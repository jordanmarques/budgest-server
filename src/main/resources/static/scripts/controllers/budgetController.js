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
          person.budgets.push($scope.modalbudget);

          PersonService.upsert(person).success(function(data){
              $scope.person = data;
              $cookies.putObject('user', data);
              delete $scope.modalbudget;
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
