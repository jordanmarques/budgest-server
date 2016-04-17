package server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.model.Person;

@NoArgsConstructor
@Getter
@Setter
public class Atendee {

    private String firstName;
    private String lastName;
    private String pseudo;
    private String phoneNumber;
    private String mail;

    public Atendee(Person person){
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.pseudo = person.getPseudo();
        this.phoneNumber = person.getPhoneNumber();
        this.mail = person.getMail();
    }




}
