'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('PersonCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService, Utils) {

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
          person.budgets.splice(person.indexOf(budget), 1);

          PersonService.upsert(person).success(function(data){
              $scope.person = data;
              $cookies.putObject('user', data);
              delete $scope.modalbudget;
          })
      }
      
      $scope.sendToDetailView = function(budget){
          $scope.detailBudget = budget;
      }

  });
