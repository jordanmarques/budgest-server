"use strict";

angular.module('budGestApp')
    .factory('BudgetService', function ($http) {

        var service = {};
        
        service.delete = function(budget){
            return $http.delete("/budget/" + budget.budgetId);
        };

        return service;

    });
