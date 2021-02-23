package mk.finki.ukim.reservations.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Restaurant restaurant;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    @ManyToOne
    private User user;
}
