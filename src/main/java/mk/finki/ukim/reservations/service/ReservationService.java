package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ReservationService {

    Reservation makeReservation(Long tableId, LocalDateTime validFrom, LocalDateTime validUntil, Long userId);

    void removeReservation(Long id);

    List<Reservation> listByRestaurant(Long restaurantId);
}
