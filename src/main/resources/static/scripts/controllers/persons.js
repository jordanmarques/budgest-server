'use strict';

/**
 * @ngdoc function
 * @name budGestApp.controller:PersonsCtrl
 * @description
 * # PersonsCtrl
 * Controller of the budGestApp
 */

/**
 * Created by Karthi on 09/04/2016.
 */




angular.module('budGestApp')
.controller('PersonsCtrl', ['$scope', 'person','$routeParams', function($scope, person,$routeParams, $timeout, $modal, $log) {
    person.success(function(data) {
        $scope.data_error = true;
        $scope.data_success = false;
        $scope.persons = data;

        $scope.detail = data[$routeParams.id];





    })

}]);



