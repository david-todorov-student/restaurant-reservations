package mk.ukim.finki.reservations.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Table table;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    @ManyToOne
    private User user;
}
