/**
 * Created by Karthi on 09/04/2016.
 */
'use strict';

angular.module('budGestApp')
    .factory('person', ['$http', function($http) {
    return $http.get('person')
        .success(function(data) {
            return data;
        })
        .error(function(data) {
            return data;
        });
}]);