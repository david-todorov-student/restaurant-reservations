package mk.finki.ukim.reservations.model.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String name) {
        super("Restaurant with name " + name + " was not found");
    }
}
