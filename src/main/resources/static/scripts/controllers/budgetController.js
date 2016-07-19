'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('BudgetCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService, BudgetService, Utils) {

      $rootScope.user = ($cookies.getObject('user') || {});
      $scope.modalbudget = {};

      if(Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/';
      }

      PersonService.getById($rootScope.user.personId).success(function(data){
          $scope.person = data;
      });

      $scope.saveBudget = function(person){

          if($scope.modalbudget.globalAmount < 1){
              alert("Un budget doit avoir un montant minimal de 1 euro")
              return;
          }

          person.budgets.push($scope.modalbudget);

          PersonService.upsert(person).success(function(data){
              $scope.person = data;
              $cookies.putObject('user', data);
              delete $scope.modalbudget;
              $('#budgetCreationModal').modal('hide');
          })
      };

      $scope.deleteBudget = function(person, budget){

          if(!confirm("Êtes vous sûre de vouloir supprimer ce budget ?")) return;

          person.budgets.forEach(function(b){
              if(b.budgetId == budget.budgetId){
                  person.budgets.splice(person.budgets.indexOf(b), 1);
              }
          });

          BudgetService.delete(budget).success(function(){
              $scope.person = person;
              $cookies.putObject('user', person);
              $scope.editMode = false;
              delete $scope.detailBudget;
          })
      };

      $scope.updateBudget = function(person, budget){
          var datePattern = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/

          var convertedstart = moment(budget.start, 'x', true).format('DD/MM/YYYY');
          if(!datePattern.test(convertedstart)){
              alert("date de début non valide");
              return;
          }

          var convertedend = moment(budget.end, 'x', true).format('DD/MM/YYYY');
          if(!datePattern.test(convertedend)){
              alert("date de fin non valide");
              return;
          }

          if(budget.globalAmount < 1){
              alert("Un budget doit avoir un montant minimal de 1 euro")
              return;
          }

          if(budget.category == ""){
              alert("Un budget doit avoir une catégorie")
              return;
          }

          delete person.budgets;
          budget.manager = person;
          
          BudgetService.upsert(budget).success(function(){
              PersonService.getById(person.personId).success(function(data){
                  $scope.detailBudget = angular.copy(budget);
                  $scope.person = data;
                  $scope.editMode = false;
              });
          })
      };
      
      $scope.sendToDetailView = function(budget){
          $scope.detailBudget = angular.copy(budget);
      };
      
      $scope.edit = function(budget){
          delete $scope.editedBudget;
          $scope.editMode = true;
          $scope.editedBudget = angular.copy(budget);
      };

  });
