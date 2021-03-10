package mk.finki.ukim.reservations.model.exceptions;

public class RestaurantAlreadyExistsException extends RuntimeException{
    public RestaurantAlreadyExistsException(String name){
        super(String.format("Restaurant with name %s already exists", name));
    }
}
