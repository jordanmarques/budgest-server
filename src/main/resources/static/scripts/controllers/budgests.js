'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:BudgestsCtrl
 * @description
 * # BudgestsCtrl
 * Controller of the budGestApp
 */
angular.module('budGestApp')
    .controller('BudgestsCtrl', function ($scope, $http) {
        /*this.awesomeThings = [
         'HTML5 Boilerplate',
         'AngularJS',
         'Karma'
         ];*/

        //$scope.budgets = [{'@jsonId':1,'id':1,'global_amount':250.0,'event':'JOJOJOJOJO','category':'JOJOJOJO','manager':{'@jsonId':2,'personId':1,'firstName':'Jonathan','lastName':'Joestar','budgets':[1]}},{'@jsonId':3,'id':2,'global_amount':15656.8,'event':'acheter un arbre','category':'JOJOJOJOJO','manager':{'@jsonId':4,'personId':2,'firstName':'Joseph','lastName':'Joestar','budgets':[3]}}];


        /*$http.get('budget').success(function (data) {

         $scope.budgets = data;
         });*/

        $http.get('budget')
            .success(function (data) {
                $scope.data_error = true;
                $scope.data_success = false;
                $scope.budgets = data;
            })
            .error(function (fail_data) {
                $scope.data_error = false;
                $scope.data_success = true;
                $scope.budgets = data;
            });

    });


angular.module('budGestApp')
    .controller('addBudget', function ($scope, $http) {
        /*var user = $scope.budget.name;
         var name = $scope.budget.name;
         var event = $scope.budget.event;
         var amount = $scope.budget.amount;*/

        $scope.master = {};

        $scope.update = function(budget) {
            $scope.master = angular.copy(budget);
        };

        $scope.reset = function() {
            $scope.budget = angular.copy($scope.master);
        };

        $scope.reset();

    });

/*
 "use strict";
 angular.module('search-service', []).factory('searchService', function ($http) {
 var service = {};
 service.search = function (filterValue) {
 return $http.get("api/ucds?filterValue=" + filterValue);
 }
 return service;
 });
 */