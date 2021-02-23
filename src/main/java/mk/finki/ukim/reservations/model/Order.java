package mk.finki.ukim.reservations.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Reservation reservation;

    @ManyToMany
    private List<Food> foodList;

    @ManyToMany
    private List<Drink> drinks;
}
