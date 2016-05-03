'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('MainCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService, Utils) {

      $scope.pseudoError = false;
      $scope.registeredMode = false;
      $scope.loginError = false;
      $scope.person = {};

      $rootScope.user = ($cookies.getObject('user') || {});

      if(!Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/user';
      }

    $scope.login = function(person){
        PersonService.login(person.username, person.password).success(function(data){

            $cookies.putObject('user', data);
            $rootScope.user = data;
            $window.location.href = '#/user';

        }).error(function(data){
            $scope.loginError = true;
        })
    };

    $scope.register = function(person){
        $scope.pseudoError = false;
        $scope.mailError = false;
        person.pseudo = person.username;



        $q.all([PersonService.checkIfPseudoExist(person.pseudo),
            PersonService.checkIfMailExist(person.mail)
        ]).then(function(data){
            $scope.pseudoError = data[0].data;
            $scope.mailError = data[1].data;

            if(!($scope.pseudoError && $scope.mailError)) {
                PersonService.upsert(person).success(function (data) {
                    $scope.registeredMode = false;
                    $scope.person.username = data.pseudo;
                    $scope.creationSuccess = true;
                })
            }

        });
    };

  });
