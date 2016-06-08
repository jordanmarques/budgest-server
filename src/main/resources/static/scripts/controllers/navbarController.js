'use strict';

angular.module('budGestApp')
  .controller('NavbarCtrl', function ($scope, $rootScope, $cookies, $window) {
      

    $scope.logout = function(){
      delete $rootScope.user;
      $cookies.remove("user");
      $window.location.href = '#/';
    };

  });
