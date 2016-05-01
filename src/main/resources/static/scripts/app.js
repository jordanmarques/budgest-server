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
                controller: 'MainCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });