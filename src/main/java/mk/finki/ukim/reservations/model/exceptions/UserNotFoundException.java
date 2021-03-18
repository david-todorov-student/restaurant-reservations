package mk.finki.ukim.reservations.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User was not found.");
    }
}
