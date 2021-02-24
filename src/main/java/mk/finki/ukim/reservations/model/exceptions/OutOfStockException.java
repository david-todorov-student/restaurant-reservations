package mk.ukim.finki.reservations.model.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Product is currently out of stock.");
    }
}
