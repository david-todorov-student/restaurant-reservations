package mk.finki.ukim.reservations.service.impl;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.reservations.repository.RestaurantRepository;
import mk.finki.ukim.reservations.repository.UserRepository;
import mk.finki.ukim.reservations.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public AuthServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public UserDetails login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new BadCredentialsException("Invalid arguments");
        }

        Restaurant restaurant = this.restaurantRepository.findByNameAndPassword(username, password).orElse(null);
        User user = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);

        if (restaurant != null) {
            return restaurant;
        } else if (user != null) {
            return user;
        } else {
            throw new InvalidUserCredentialsException();
        }

    }
}
