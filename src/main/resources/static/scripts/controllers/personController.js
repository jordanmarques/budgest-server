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

      $scope.person = $rootScope.user;

      if(Utils.isEmpty($scope.person)){
          $window.location.href = '#/';
      }

  });
