package mk.finki.ukim.reservations.repository;

import mk.finki.ukim.reservations.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, String> {
}
