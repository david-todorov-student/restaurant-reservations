package mk.finki.ukim.reservations.config;

import lombok.Getter;
import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Restaurant> restaurants = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();
    public static List<Table> tables = new ArrayList<>();


    @PostConstruct
    public void initData(){
        for(int i=1; i<=5; i++){
            tables.add(new Table(2*i));
        }
    }
}
