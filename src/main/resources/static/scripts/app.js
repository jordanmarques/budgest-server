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
            }).when('/user', {
                templateUrl: 'views/person.html',
                controller: 'PersonCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }).factory('Utils', function() {
        var service = {};

        service.isEmpty = function(obj){
            if (obj == null) return true;

            if (obj.length > 0)    return false;
            if (obj.length === 0)  return true;

            for (var key in obj) {
                if (hasOwnProperty.call(obj, key)) return false;
            }

            return true;
        };

        return service;
});