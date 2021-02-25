package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    UserDetails loadRestaurantByName(String s);

    Restaurant register(String name, String password, String repeatPassword, String address, String city, String country, double latitude, double longitude);

}
