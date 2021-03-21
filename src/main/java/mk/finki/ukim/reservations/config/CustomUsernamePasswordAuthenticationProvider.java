package mk.finki.ukim.reservations.config;

import mk.finki.ukim.reservations.service.AuthService;
import mk.finki.ukim.reservations.service.RestaurantService;
import mk.finki.ukim.reservations.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(UserService userService, RestaurantService restaurantService, AuthService authService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username.isEmpty() || password.isEmpty()) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        UserDetails userDetailsUser = null;
        UserDetails userDetailsRestaurant = null;

        try {
            userDetailsUser = this.userService.loadUserByUsername(username);
        } catch (Exception e) {
        }

        try {
            userDetailsRestaurant = this.restaurantService.loadRestaurantByName(username);
        } catch (Exception e) {
        }

        UserDetails userDetails;

        if (userDetailsUser != null) {
            if (passwordEncoder.matches(password, userDetailsUser.getPassword())) {
                userDetails = userDetailsUser;
            } else {
                throw new BadCredentialsException("Password is incorrect!");
            }
        } else if (userDetailsRestaurant != null) {
            if (passwordEncoder.matches(password, userDetailsRestaurant.getPassword())) {
                userDetails = userDetailsRestaurant;
            } else {
                throw new BadCredentialsException("Password is incorrect!");
            }
        } else {
            throw new UsernameNotFoundException(username);
        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
