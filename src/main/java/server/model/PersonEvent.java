package server.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_event")
@AssociationOverrides({
        @AssociationOverride(name = "personEventId.person", joinColumns = @JoinColumn(name = "id_person")),
        @AssociationOverride(name = "personEventId.event", joinColumns = @JoinColumn(name = "id_event")) })
public class PersonEvent {

    @EmbeddedId
    private PersonEventId personEventId = new PersonEventId();

    @Column(name = "is_owner")
    private Boolean isOwner;

    @Transient
    public Person getPerson(){
        return getPersonEventId().getPerson();
    }
    public void setPerson(Person person){
        getPersonEventId().setPerson(person);
    }

    @Transient
    public Event getEvent(){
        return getPersonEventId().getEvent();
    }
    public void setEvent(Event event){
        getPersonEventId().setEvent(event);
    }
}
