package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDetails loadUserByUsername(String s);

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
