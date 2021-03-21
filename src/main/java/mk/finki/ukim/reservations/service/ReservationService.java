package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ReservationService {

    Reservation makeReservation(Long tableId, LocalDateTime validFrom, LocalDateTime validUntil, User user);

    void removeReservation(Long id);

    List<Reservation> listByRestaurant(Long restaurantId);

    List<Reservation> getPlacedReservationsForUser(User user);

//    Reservation placeReservation(User user, List<Reservation> reservations);

    Reservation getActiveReservation(Long tableId, LocalDateTime validFrom, LocalDateTime validUntil, String username);

//    List<Restaurant> getAllRestaurantsInUserActiveReservations(String username);

    void changeStatusToFinishedOfActiveReservationList(Long tableId, LocalDateTime validFrom, LocalDateTime validUntil, String username);
}
