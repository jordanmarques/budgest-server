"use strict";

angular.module('budGestApp')
    .factory('PersonService', function ($http) {

        var service = {};

        service.login = function(id, password){
            return $http.get("/person/login?id=" + id + "&password=" + password);
        };

        service.getById = function(id){
            return $http.get("/person/" + id);
        };

        service.upsert = function(person){
            return $http.post("/person", person);
        };

        service.checkIfMailExist = function(mail){
            return $http.get("/person/exist/mail/" + mail);
        };

        service.checkIfPseudoExist = function(pseudo){
            return $http.get("/person/exist/pseudo/" + pseudo);
        };

        return service;

    });
