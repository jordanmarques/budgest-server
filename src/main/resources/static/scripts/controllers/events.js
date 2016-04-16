'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:EventsCtrl
 * @description
 * # EventsCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
  .controller('EventsCtrl', function ($scope, $http, person) {

      //Ajout de l event
      person.success(function (data) {
          $scope.persons = data;
      });

      $scope.addEvent = function (event) {

          $http.post('event/', event)
              .success(function () {
                  alert('Une e');
              })
              .error(function (data) {
                  alert('Une erreur est survenue lors de la récupération de l\'utilisateur: ' + data.message);
              });
      };


      //Affichage des event
      $http.get('event')
          .success(function (data) {
              $scope.data_error = true;
              $scope.data_success = false;
              $scope.events = data;
          })
          .error(function () {
              $scope.data_error = false;
              $scope.data_success = true;
          });
  });
