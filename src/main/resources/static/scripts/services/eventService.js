"use strict";

angular.module('budGestApp')
    .factory('EventService', function ($http) {

        var service = {};

        service.upsert = function(event){
            return $http.post("/event", event);
        };
        
        service.delete = function(event){
            return $http.delete("/event/" + event.eventId);
        };

        return service;

    });
