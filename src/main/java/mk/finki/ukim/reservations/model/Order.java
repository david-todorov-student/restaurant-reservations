package mk.finki.ukim.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
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

    public Order(Reservation reservation) {
        this.reservation = reservation;
        this.foodList = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }
}
