"use strict";

angular.module('budGestApp')
    .factory('personService', function ($http) {

        var service = {};

        service.login = function(id, password){
            return $http.get("/person/login?id=" + id + "&password=" + password);
        };

        return service;

    });
