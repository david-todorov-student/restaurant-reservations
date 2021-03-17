package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {
    List<Restaurant> listAll();

    UserDetails loadRestaurantByName(String s);

    Restaurant register(String name, String password, String repeatPassword, String address, String city, String country, double latitude, double longitude);

    List<Restaurant> filterByText(String text);

    void deleteById(Long id);

    Optional<Restaurant> findById(Long id);
}
