package server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPerson")
    private long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<Budget> budgets;



    public Person(long id, String firstName, String lastName) {
        this.personId = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
