package mk.finki.ukim.reservations.model.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
        super("Restaurant was not found");
    }
}
