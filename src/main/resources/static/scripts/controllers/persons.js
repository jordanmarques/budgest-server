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
    .controller('PersonCtrl', function ($scope, $http, $routeParams, person, $route, $location) {

        person
            .success(function (data) {
                $scope.persons = data;
            });


        $http.get('person/' + $routeParams.id)
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.detail = data;
            })
            .error(function () {
                alert('Ce person n\'existe pas !');
                $location.path('#/Persons');
            });


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

        $scope.delete = function (budget) {
            $http.delete('budget/' + budget.budgetId)
                .success(function () {
                    alert('Le budget a bien supprimé');
                    $route.reload();
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la suppression ddu budget : ' + data.message);
                });
        };


        //EVENT


        $scope.updateEventShow = function (event) {
            $scope.event_update = true;
            $scope.eventUpdate = event;
        };

        $scope.updateEventAction = function (event) {
            delete event.atendees;
            $http.post('event/', event)
                .success(function () {
                    alert('L\'évènement a bien été mis à jours !');
                    $route.reload();
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la mise à jours de l\'évènement : ' + data.message);
                });
        };

    });

