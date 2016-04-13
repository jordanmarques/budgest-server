package server.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;
import server.dto.Atendee;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_event")
    private Long eventId;

    @Column(name = "name")
    @NotEmpty(message = "An event must have a name.")
    private String name;

    @Column(name = "amount")
    @DecimalMin(value = "1", message = "Amount must be equal or bigger than 1")
    private Float amount;

    @Column(name = "id_owner")
    @NotNull
    private Long ownerId;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "events")
    private Set<Person> persons;

    @JsonProperty
    public List<Atendee> getAtendees(){
        return persons.stream().map(Atendee::new).collect(Collectors.toList());
    }

}
