package mk.finki.ukim.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int seats;

    @ManyToOne
    private Restaurant restaurant;

    public Table(int seats) {
        this.seats = seats;
//        this.restaurant = restaurant;
    }
}
