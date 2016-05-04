"use strict";

angular.module('budGestApp')
    .factory('BudgetService', function ($http) {

        var service = {};

        service.upsert = function(budget){
            return $http.post("/budget", budget);
        };
        
        service.delete = function(budget){
            return $http.delete("/budget/" + budget.budgetId);
        };

        return service;

    });
