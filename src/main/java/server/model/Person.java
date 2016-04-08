package server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "person")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person")
    private Long personId;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "pseudo")
    @NotNull
    private String pseudo;

    @Column(name = "password")      // TODO => encrypt the password
    @NotNull
    private String password;

    @Column(name = "birthdate")
    @Null
    private Date birthdate;

    @Column(name = "phone_number")
    @Null
    private String phoneNumber;

    @Column(name = "mail")
    @NotNull
    private String mail;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @Null
    private Set<Budget> budgets;

}
