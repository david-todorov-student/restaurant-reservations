package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails login(String username, String password);

}
