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

          if($scope.modalevent.amount < 1){
              alert("Un budget d'événement doit avoir un montant minimal de 1 euro")
              return;
          }

          $scope.modalevent.ownerId = person.personId;
          person.events.push($scope.modalevent);

          PersonService.upsert(person).success(function(data){
              PersonService.getById(data.personId).success(function(data2){
                  $scope.person = data2;
                  $cookies.putObject('user', data);
                  delete $scope.modalevent;
                  $('#eventCreationModal').modal('hide');
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
              $scope.editMode = false;
              delete $scope.detailEvent;
          })
      };

      $scope.updateEvent = function(person, event){
          delete person.events;

          if(event.amount < 1){
              alert("Un budget d'événement doit avoir un montant minimal de 1 euro")
              return;
          }

          if(event.category == ""){
              alert("Un événement doit avoir une catégorie")
              return;
          }

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
          delete $scope.editedEvent;
          $scope.editMode = true;
          $scope.editedEvent = angular.copy(event);
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
          var index = -1;

          for(var i=0; i<$scope.invitList.length; i++){
              if($scope.invitList[i].eventId == eventId && $scope.invitList[i].personId == personId){
                  index = i;
              }
          }

          if(index == -1){
              $scope.invitList.push(invit);
          }else{
              $scope.invitList.splice(index, 1)
          }
          
      };
      
      $scope.invitPersons = function(){
          $scope.invitList.forEach(function(invit){
              InvitationService.save(invit)
          });
          $('#invitModal').modal('hide');
      };
      
      $scope.leave = function(event){
          $scope.person.events.forEach(function(e){
              if(!confirm("Êtes vous sûr de vouloir quitter cet événement ?")) return;
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

      $scope.kick = function(person){
          if(!confirm("Êtes vous sûr de vouloir retirer cet personne de l'événement ?")) return;
          PersonService.getById(person.id).success(function(data){
              var dbPerson = data;
              dbPerson.events.forEach(function(e){
                  if(e.eventId == $scope.detailEvent.eventId){
                      dbPerson.events.splice(dbPerson.events.indexOf(e),1);

                      $scope.detailEvent.atendees.forEach(function (p) {
                          if(p.id == person.id){
                              $scope.detailEvent.atendees.splice($scope.detailEvent.atendees.indexOf(p),1)
                          }
                      })

                      PersonService.upsert(dbPerson).success(function(data){

                      })
                  }
              })
          })
      }

  });
