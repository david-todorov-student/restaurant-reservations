package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Restaurant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {
    List<Restaurant> listAll();

    UserDetails loadRestaurantByName(String s);

    Restaurant register(String name, String password, String repeatPassword, String address, String city, String country, double latitude, double longitude);

    Restaurant save(String name, String address, String city, String country, double longitude, double latitude);

    Restaurant edit(Long id, String name, String address, String city, String country, double longitude, double latitude);

    void deleteById(Long id);

    Optional<Restaurant> findById(Long id);

    List<Restaurant> searchByNameOrCity(String text);
}
