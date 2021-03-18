package mk.finki.ukim.reservations.repository;

import mk.finki.ukim.reservations.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNameOrCity(String name, String city);
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> findByCity(String city);
    Optional<Restaurant> findByNameAndPassword(String name, String password);
}
