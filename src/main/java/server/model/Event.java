package server.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_event")
    private Long eventId;

    @Column(name = "amount")
    private Float amount;

    @OneToMany(mappedBy = "personEventId.event", cascade = CascadeType.ALL)
    private Set<PersonEvent> persons;



}
