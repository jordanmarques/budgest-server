package server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invitation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "personId")
    @NotEmpty(message = "A person Id cannot be null")
    private String personId;

    @Column(name = "eventId")
    @NotEmpty(message = "An event Id cannot be null")
    private String eventId;
}
