package server.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "budget")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_budget")
    private Long budgetId;

    @Column(name = "name")
    @NotEmpty(message = "A budget must have a name")
    private String name;

    @Column(name = "global_amount")
    @DecimalMin(value = "1", message = "Amount must be equal or bigger than 1")
    private Float globalAmount;

    @ManyToOne
    @JoinColumn(name="id_person")
    private Person manager;
}

