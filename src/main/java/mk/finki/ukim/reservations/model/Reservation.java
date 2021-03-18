package mk.finki.ukim.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "reservations")
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

    public Reservation(Table table, LocalDateTime validFrom, LocalDateTime validUntil, User user) {
        this.table = table;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.user = user;
    }
}
