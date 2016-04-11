package server.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonEventId implements Serializable{

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;
}
