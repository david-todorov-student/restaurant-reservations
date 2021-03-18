package mk.finki.ukim.reservations.repository;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);

    List<Reservation> findAllByTableRestaurant(Restaurant restaurant);


}
