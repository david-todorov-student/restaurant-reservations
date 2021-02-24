package mk.ukim.finki.reservations.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT, ROLE_RESTAURANT, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}