'use strict';

angular.module('budGestApp')
  .controller('EventCtrl', function ($scope, $rootScope, $cookies, $q, $window, PersonService, EventService, Utils, InvitationService) {

      $rootScope.user = ($cookies.getObject('user') || {});
      $scope.modalevent = {};
      $scope.eventInvitations = [];
      $scope.invitList = [];

      if(Utils.isEmpty($rootScope.user)){
          $window.location.href = '#/';
      }

      PersonService.getById($rootScope.user.personId).success(function(data){
          $scope.person = data;
          getInvitations();
      });


      function getInvitations(){
          InvitationService.findByPersonId($scope.person.personId).success(function(data){
              var invitations = data;
              invitations.forEach(function(invit){
                  EventService.getById(invit.eventId).success(function(data){
                      data.invitId = invit.id;
                      $scope.eventInvitations.push(data);
                  })
              })

          });
      }

      $scope.saveEvent = function(person){
          $scope.modalevent.ownerId = person.personId;
          person.events.push($scope.modalevent);

          PersonService.upsert(person).success(function(data){
              PersonService.getById(data.personId).success(function(data2){
                  $scope.person = data2;
                  $cookies.putObject('user', data);
                  delete $scope.modalevent;
              });

              
          })
      };


      $scope.deleteEvent = function(person, event){

          if(!confirm("Êtes vous sûre de vouloir supprimer cet événement ?")) return;
          
          person.events.forEach(function(e){
             if(e.eventId == event.eventId){
                 person.events.splice(person.events.indexOf(e), 1);
             } 
          });
          

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
                  $scope.editMode = false;
                  PersonService.getById(data.personId).success(function(data2){
                      $scope.person = data2;
                      $cookies.putObject('user', data);
                      delete $scope.modalevent;
                  });

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

      $scope.acceptInvitation = function(index, invitation){
          $scope.person.events.push(invitation);
          PersonService.upsert($scope.person).success(function(data){
              PersonService.getById(data.personId).success(function(data2){
                  $scope.person = data2;
                  $cookies.putObject('user', data);
                  delete $scope.modalevent;
                  $scope.declineInvitation(index, invitation);
              });

              
              
          })

      };

      $scope.declineInvitation = function(index, invitation){
          InvitationService.delete(invitation.invitId).success(function (data) {
              $scope.eventInvitations.splice(index, 1);
          })
      };

      $scope.addToInvitList = function(personId, eventId){
          var invit = {personId:personId, eventId:eventId};
          $scope.invitList.push(invit);
          
      };
      
      $scope.invitPersons = function(){
          $scope.invitList.forEach(function(invit){
              InvitationService.save(invit)
          });
          $('#invitModal').modal('hide');
      };
      
      $scope.leave = function(event){
          $scope.person.events.forEach(function(e){
              if(event.eventId == e.eventId){
                  $scope.person.events.splice($scope.person.events.indexOf(e),1);
              }
          });
          PersonService.upsert($scope.person).success(function(data){
              PersonService.getById(data.personId).success(function(data2){
                  $scope.person = data2;
                  $cookies.putObject('user', data);
                  delete $scope.detailEvent;
              });
          })
      }

  });
