package server.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "budget")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_budget")
    private Long budgetId;

    @Column(name = "name")
    @NotEmpty(message = "A budget must have a name")
    private String name;

    @Column(name = "category")
    @NotEmpty(message = "a budget must have a category")
    private String category;

    @Column(name = "start")
    @NotNull(message = "a budget must have a begining date")
    private Date start;

    @Column(name = "end")
    @NotNull(message = "a budget must have a ending date")
    private Date end;

    @Column(name = "global_amount")
    @DecimalMin(value = "1", message = "Amount must be equal or bigger than 1")
    private Float globalAmount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_person")
    private Person manager;
}

