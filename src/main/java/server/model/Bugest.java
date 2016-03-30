package server.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
public class Bugest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Column(name = "global_amount")
    private float global_amount;

    @NotNull
    @Column(name = "event")
    private String event;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "Gérant")
    private Person manager;

    public Bugest(String id, float global_amount,String event, String category,Person manager) {
        this.id = id;
        this.global_amount = global_amount;
        this.event = event;
        this.category = category;
        this.manager = manager;
    }
}
