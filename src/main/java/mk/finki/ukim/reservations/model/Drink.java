package mk.finki.ukim.reservations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.reservations.model.exceptions.OutOfStockException;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink {
    @Id
    private String name;

    private int quantity;

    public void sell(int quantity){
        if (this.quantity > 0){
            this.quantity-=quantity;
        }
        else throw new OutOfStockException();
    }

    public void buy(int quantity){
        this.quantity+=quantity;
    }
}
