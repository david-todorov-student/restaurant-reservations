package mk.finki.ukim.reservations.model.exceptions;

public class TableNotFoundException extends RuntimeException{
    public TableNotFoundException() {
        super("Table was not found");
    }
}
