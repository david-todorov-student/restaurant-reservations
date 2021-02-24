package mk.finki.ukim.reservations.model.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Product is currently out of stock.");
    }
}
