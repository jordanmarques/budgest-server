'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('MainCtrl', function ($scope, personService) {

    $scope.registeredMode = false;
    $scope.person = {};

    $scope.login = function(person){
        personService.login(person.username, person.password).success(function(data){
            $scope.retriviedPerson = data;
        }).error(function(data){

        })
    };

    $scope.register = function(person){

    }

  });
