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
      .when('/persons', {
        templateUrl: 'views/persons.html',
        controller: 'PersonsCtrl',
        controllerAs: 'persons'
      })
      .when('/budgests', {
        templateUrl: 'views/budgests.html',
        controller: 'BudgestsCtrl',
        controllerAs: 'budgests'
      })
      .when('/budgests', {
        templateUrl: 'views/budgests.html',
        controller: 'BudgestsCtrl',
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
