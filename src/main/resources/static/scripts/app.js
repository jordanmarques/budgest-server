'use strict';

/**
 * @ngdoc overview
 * @name budGestApp
 * @description
 * # budGestApp
 *
 * Main module of the application.
 */
angular
    .module('budGestApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl',
                controllerAs: 'main'
            })
            .when('/persons/add', {
                templateUrl: 'views/persons/addPerson.html',
                controller: 'PersonsCtrl',
                controllerAs: 'persons'
            })
            .when('/persons', {
                templateUrl: 'views/persons/mainPersons.html',
                controller: 'PersonsCtrl',
                controllerAs: 'persons'
            })
            .when('/persons/:id', {
                templateUrl: 'views/persons/personDetail.html',
                controller: 'PersonCtrl',
                controllerAs: 'persons'
            })
            .when('/persons/:id/update', {
                templateUrl: 'views/persons/updatePerson.html',
                controller: 'PersonCtrl',
                controllerAs: 'persons'
            })
            .when('/budgests', {
                templateUrl: 'views/budgests/budgests.html',
                controller: 'BudgestsCtrl',
                controllerAs: 'budgests'
            })
            .when('/budgests/:id', {
                templateUrl: 'views/budgests/budgetDetail.html',
                controller: 'BudgestCtrl',
                controllerAs: 'budgests'
            })
            .when('/events', {
                templateUrl: 'views/events.html',
                controller: 'EventsCtrl',
                controllerAs: 'events'
            })
            .otherwise({
                redirectTo: '/'
            });
    });