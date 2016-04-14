'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:BudgestsCtrl
 * @description
 * # BudgestsCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
    .controller('BudgestsCtrl', function ($scope, $http) {

        $http.get('budget')
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.budgets = data;
            })
            .error(function () {
                $scope.data_error = false;
                $scope.data_success = true;
            });

    });


angular.module('budGestApp')
    .controller('addBudget', function ($scope, $http, person, $route) {

        person.success(function (data) {
            $scope.persons = data;
        });

        $scope.dataObj = {};

        $scope.update = function (budget) {

            $http.get('person/' + $scope.budget.user)
                .success(function (data) {
                    var user = data;

                    $scope.dataObj = {
                        name: $scope.budget.name,
                        globalAmount: $scope.budget.global_amount,
                        manager: {
                            personId: user.personId,
                            firstName: user.firstName,
                            lastName: user.lastName,
                            pseudo: user.pseudo,
                            password: user.password,
                            birthdate: user.birthdate,
                            phoneNumber: user.phoneNumber,
                            mail: user.mail
                        }
                    };

                    $scope.addBudgetPost(user);
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la récupération de l\'utilisateur: ' + data.message);
                });
        };

        $scope.addBudgetPost = function (user) {
            var data = {
                name: $scope.budget.name,
                globalAmount: $scope.budget.global_amount,
                manager: {
                    personId: user.personId,
                    firstName: user.firstName,
                    lastName: user.lastName,
                    pseudo: user.pseudo,
                    password: user.password,
                    birthdate: user.birthdate,
                    phoneNumber: user.phoneNumber,
                    mail: user.mail
                }
            };

            $http.post('budget/', data)
                .success(function (data) {
                    alert('Ce budget a bien été crée.');
                    //$scope.reset();
                    $route.reload();
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de l\'ajout du budget : ' + data.message);
                });
        }

        $scope.reset = function () {
            $scope.budget = {};
        };

        $scope.reset();

    });

angular.module('budGestApp')
    .controller('BudgestCtrl', function ($scope, $http, $routeParams, person, $route, $location) {

        person
            .success(function (data) {
                $scope.persons = data;
            });

        /*.error(function () {
         alert('Ce budget n\'existe pas !');
         $location.path('#/budgests');
         });*/

        $http.get('budget/' + $routeParams.id)
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.budget = data;
            })
            .error(function () {
                alert('Ce budget n\'existe pas !');
                $location.path('#/budgests');
            });

        $scope.update = function (budget) {

            $http.post('budget', $scope.budget)
                .success(function (data) {
                    alert('Le budget a bien été mis à jours');
                    $route.reload();
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la mise à jours du budget : ' + data.message);
                });
        };

        $scope.delete = function (budget) {
            $http.delete('budget/' + $routeParams.id)
                .success(function (data) {
                    alert('Le budget a bien supprimé');
                    $location.path('#/budgests');
                })
                .error(function (data) {
                    alert('Une erreur est survenue lors de la suppression ddu budget : ' + data.message);
                });
        };

    });
//EXEMPLE DE SERVICE
/*
 "use strict";
 angular.module('search-service', []).factory('searchService', function ($http) {
 var service = {};
 service.search = function (filterValue) {
 return $http.get("api/ucds?filterValue=" + filterValue);
 }
 return service;
 });
 */