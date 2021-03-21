package mk.finki.ukim.reservations.service.impl;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.enumerations.ReservationStatus;
import mk.finki.ukim.reservations.model.exceptions.RestaurantNotFoundException;
import mk.finki.ukim.reservations.model.exceptions.TableNotFoundException;
import mk.finki.ukim.reservations.model.exceptions.UserNotFoundException;
import mk.finki.ukim.reservations.repository.ReservationRepository;
import mk.finki.ukim.reservations.repository.RestaurantRepository;
import mk.finki.ukim.reservations.repository.TableRepository;
import mk.finki.ukim.reservations.repository.UserRepository;
import mk.finki.ukim.reservations.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  TableRepository tableRepository,
                                  UserRepository userRepository,
                                  RestaurantRepository restaurantRepository) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Reservation makeReservation(Long tableId, Date validFrom, Date validUntil, User user, Restaurant restaurant) {
        Table table = this.tableRepository.findById(tableId)
                .orElseThrow(TableNotFoundException::new);
//        User user = this.userRepository.findById(userId)
//                .orElseThrow(UserNotFoundException::new);
        Reservation reservation = new Reservation(table, validFrom, validUntil, user, restaurant);

        this.reservationRepository.save(reservation);
        this.changeStatusToFinishedOfActiveReservationList(tableId, validFrom, validUntil, user.getUsername(), restaurant);

        return reservation;
    }

    @Override
    public void removeReservation(Long id) {
        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> listByRestaurant(Long restaurantId) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        return this.reservationRepository.findAllByTableRestaurant(restaurant);
    }

    @Override
    public List<Reservation> getPlacedReservationsForUser(User user) {
        if (user == null){
            throw new UserNotFoundException(user.getUsername());
        }

        return reservationRepository.findAllByUser(user);
    }

//    @Override
//    public List<Restaurant> getAllRestaurantsInUserActiveReservations(String username) {
//        Reservation activeReservation = this.getActiveReservation(username);
//        return activeReservation.getRestaurants();
//    }

    @Override
    public void changeStatusToFinishedOfActiveReservationList(Long tableId, Date validFrom, Date validUntil, String username, Restaurant restaurant) {
        Reservation activeReservation = this.getActiveReservation(tableId, validFrom, validUntil, username, restaurant);
        activeReservation.setStatus(ReservationStatus.FINISHED);
        reservationRepository.save(activeReservation);
    }

    @Override
    public Reservation getActiveReservation(Long tableId, Date validFrom, Date validUntil, String username, Restaurant restaurant) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.reservationRepository
                .findByUser(user.getUsername())
                .orElseGet(() -> {
                    Table table = this.tableRepository.findById(tableId).orElseThrow(TableNotFoundException::new);
                    Reservation reservation = new Reservation(table, validFrom, validUntil, user, restaurant);
                    return this.reservationRepository.save(reservation);
                });

    }

}
