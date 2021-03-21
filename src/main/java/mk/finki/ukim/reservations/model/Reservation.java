package mk.finki.ukim.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.reservations.model.enumerations.ReservationStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date validFrom;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date validUntil;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    private ReservationStatus status;

    public Reservation(Table table, Date validFrom, Date validUntil, User user, Restaurant restaurant) {
        this.table = table;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.user = user;
        this.restaurant = restaurant;
        this.status = ReservationStatus.CREATED;
    }
}
