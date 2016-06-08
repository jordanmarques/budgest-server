"use strict";

angular.module('budGestApp')
    .factory('InvitationService', function ($http) {

        var service = {};

        service.save = function(invit){
            return $http.post("/invit", invit);
        };
        
        service.delete = function(invitId){
            return $http.delete("/invit/" + invitId);
        };
        
        service.findByPersonId = function(personId){
            return $http.get("/invit/person/" + personId);
        };

        return service;

    });
