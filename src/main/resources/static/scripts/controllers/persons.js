'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:PersonsCtrl
 * @description
 * # PersonsCtrl
 * Controller of the budGestApp
 */

/**
 * Created by Karthi on 09/04/2016.
 */

angular.module('budGestApp')
    .controller('PersonsCtrl', function ($scope, $http) {

        $http.get('person')
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.persons = data;
            })
            .error(function () {
                $scope.data_error = false;
                $scope.data_success = true;
            });

    });


angular.module('budGestApp')
    .controller('PersonsCtrl', function ($scope, $http, $routeParams, person, $route, $location) {

        person
            .success(function (data) {
                $scope.persons = data;
            });


        $http.get('person/' + $routeParams.id)
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.detail = data;
            });
       /*     .error(function () {                                          REgard ça Faisal, quand j'enleve le commentaire, j'ai à chaque fois un popup http://localhost:8080/person/undefined
                alert('Ce person n\'existe pas !');                         si t'as le temps de jeter un coup d'oeil, Merci d'avance
                $location.path('#/Persons');
            });*/


        $scope.DeleteData = function (idx) {
            console.log(idx);

            var idPerson=idx;

            $http.delete('person/' +  idPerson)
                .success(function (data) {
                    alert('La personne a bien été supprimer ');
                    $route.reload();
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la suppression de cette personne : ' + data.message);
                });
        };

    });

