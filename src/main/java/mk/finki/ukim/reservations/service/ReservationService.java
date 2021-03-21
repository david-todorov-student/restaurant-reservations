package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface ReservationService {

    Reservation makeReservation(Long tableId, Date validFrom, Date validUntil, User user, Restaurant restaurant);

    void removeReservation(Long id);

    List<Reservation> listByRestaurant(Long restaurantId);

    List<Reservation> getPlacedReservationsForUser(User user);

//    Reservation placeReservation(User user, List<Reservation> reservations);

    Reservation getActiveReservation(Long tableId, Date validFrom, Date validUntil, String username, Restaurant restaurant);

//    List<Restaurant> getAllRestaurantsInUserActiveReservations(String username);

    void changeStatusToFinishedOfActiveReservationList(Long tableId, Date validFrom, Date validUntil, String username, Restaurant restaurant);
}
