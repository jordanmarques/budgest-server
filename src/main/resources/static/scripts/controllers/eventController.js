'use strict';

angular.module('budGestApp')
  .controller('EventCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService, EventService, Utils) {

      $rootScope.user = ($cookies.getObject('user') || {});
      $scope.modalevent = {};

      if(Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/';
      }

      PersonService.getById($rootScope.user.personId).success(function(data){
          $scope.person = data;
      });

      $scope.saveEvent = function(person){
          $scope.modalevent.ownerId = person.personId;
          person.events.push($scope.modalevent);

          PersonService.upsert(person).success(function(data){
              $scope.person = data;
              $cookies.putObject('user', data);
              delete $scope.modalevent;
          })
      };

      $scope.deleteEvent = function(person, event){

          if(!confirm("Êtes vous sûre de vouloir supprimer cet événement ?")) return;

          person.events.splice(person.events.indexOf(event), 1);

          EventService.delete(event).success(function(){
              $scope.person = person;
              $cookies.putObject('user', person);
              delete $scope.detailEvent;
          })
      };

      $scope.updateEvent = function(person, event){
          delete person.events;

          EventService.upsert(event).success(function(){
              PersonService.getById(person.personId).success(function(data){
                  $scope.detailEvent = angular.copy(event);
                  $scope.person = data;
                  $scope.editMode = false;
              });
          })
      };
      
      $scope.sendToDetailView = function(event){
          $scope.detailEvent = angular.copy(event);
      };
      
      $scope.edit = function(event){
          delete $scope.editedBudget;
          $scope.editMode = true;
          $scope.editedBudget = angular.copy(event);
      };


      $scope.triggerModal = function(){
          $('#invitModal').modal('show');
          $scope.invitLoading = true;
          PersonService.getAll().success(function(data){
              $scope.persons = data;
              delete $scope.invitLoading;
          })
      };

  });
