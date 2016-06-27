'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('PersonCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService,Utils) {

      $rootScope.user = ($cookies.getObject('user') || {});
      $scope.modalbudget = {};

      if(Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/';
      }

      PersonService.getById($rootScope.user.personId).success(function(data){
          $scope.person = data;
      });

      $scope.changePassword = function() {
          if ($scope.newPassword !== $scope.confirmPassword || (Utils.isEmpty($scope.newPassword) && Utils.isEmpty($scope.confirmPassword)))  {
              alert("Les mots de passe ne correspondent pas");
          } else {
              $scope.person.password = $scope.confirmPassword;

              PersonService.upsert($scope.person).success(function (data) {
                  $scope.person = data;
                  $cookies.putObject('user', data);
                  delete $scope.newPassword;
                  delete $scope.confirmPassword;
                  $scope.passwordSuccessAlert = true;
              })
          }
      }


  });
