package mk.finki.ukim.reservations.service.impl;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
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
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, TableRepository tableRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Reservation makeReservation(Long tableId, LocalDateTime validFrom, LocalDateTime validUntil, Long userId) {
        Table table = this.tableRepository.findById(tableId)
                .orElseThrow(TableNotFoundException::new);
        User user = this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Reservation reservation = new Reservation(table, validFrom, validUntil, user);
        return this.reservationRepository.save(reservation);
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


}
