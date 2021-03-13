package mk.finki.ukim.reservations.service.impl;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.reservations.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.reservations.model.exceptions.RestaurantNotFoundException;
import mk.finki.ukim.reservations.repository.RestaurantRepository;
import mk.finki.ukim.reservations.service.RestaurantService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Restaurant> listAll() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public UserDetails loadRestaurantByName(String s) {
        return restaurantRepository.findByName(s).orElseThrow(() -> new RestaurantNotFoundException(s));
    }

    @Override
    public Restaurant register(String name, String password, String repeatPassword, String address,
                               String city, String country, double latitude, double longitude) {
        if (name == null || name.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUserCredentialsException();

        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        Restaurant restaurant = new Restaurant(name, passwordEncoder.encode(password), address, city, country, latitude, longitude);
        return restaurantRepository.save(restaurant);
    }
}
