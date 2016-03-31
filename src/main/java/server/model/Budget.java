package server.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table (name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBudget")
    private long id;

    @Column(name = "global_amount")
    private float global_amount;

    @Column(name = "event")
    private String event;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person manager;

    public Budget(long id, float global_amount, String event, String category, Person manager) {
        this.id = id;
        this.global_amount = global_amount;
        this.event = event;
        this.category = category;
        this.manager = manager;
    }


}

