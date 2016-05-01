'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('MainCtrl', function ($scope, $q, PersonService) {

      $scope.pseudoError = false;
      $scope.registeredMode = false;
      $scope.loginError = false;
      $scope.person = {};

      $("#registerForm").validator();

    $scope.login = function(person){
        PersonService.login(person.username, person.password).success(function(data){
            $scope.retriviedPerson = data;
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
                PersonService.create(person).success(function (data) {
                    $scope.registeredMode = false;
                    $scope.person.username = data.pseudo;
                    $scope.creationSuccess = true;
                })
            }

        });



    }

  });
