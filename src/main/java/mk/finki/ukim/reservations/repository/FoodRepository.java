package mk.finki.ukim.reservations.repository;

import mk.finki.ukim.reservations.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
}
