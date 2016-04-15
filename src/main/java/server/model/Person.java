package server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "person")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person")
    private Long personId;

    @Column(name = "first_name")
    @NotEmpty(message = "A person must have a first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "A person must have a last name")
    private String lastName;

    @Column(name = "pseudo")
    @NotEmpty(message = "A person must have a pseudonym")
    private String pseudo;

    @Column(name = "password")      // TODO => encrypt the password
    @NotNull                        // Can be empty ???
    private String password;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail")
    @NotEmpty(message = "A person must have a mail")
    private String mail;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager", cascade = CascadeType.MERGE)
    private Set<Budget> budgets;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "person_event", joinColumns = {
            @JoinColumn(name = "id_person", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_event", nullable = false, updatable = false)})
    private Set<Event> events;

}
