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

      if(Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/';
      }

      PersonService.getById($rootScope.user.personId).success(function(data){
          $scope.person = data;
      });



  });
