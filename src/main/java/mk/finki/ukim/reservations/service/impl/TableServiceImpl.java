package mk.finki.ukim.reservations.service.impl;

import mk.finki.ukim.reservations.config.DataHolder;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.exceptions.RestaurantNotFoundException;
import mk.finki.ukim.reservations.model.exceptions.TableNotFoundException;
import mk.finki.ukim.reservations.repository.RestaurantRepository;
import mk.finki.ukim.reservations.repository.TableRepository;
import mk.finki.ukim.reservations.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public TableServiceImpl(TableRepository tableRepository, RestaurantRepository restaurantRepository) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Table addTable(int seats, Long restaurantId) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
//        Table table = new Table(seats, restaurant);
        Table table = new Table(seats);
        return this.tableRepository.save(table);
    }

    @Override
    public Table editTable(Long tableId, int seats, Long restaurantId) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
        Table table = this.tableRepository.findById(tableId)
                .orElseThrow(TableNotFoundException::new);

        table.setSeats(seats);
//        table.setRestaurant(restaurant);

        return this.tableRepository.save(table);
    }

    @Override
    public void deleteTable(Long tableId) {
        this.tableRepository.deleteById(tableId);
    }

    @Override
    public List<Table> listAll() {
        return DataHolder.tables;
    }

    public Optional<Table> findById(Long tableId) {
        return this.tableRepository.findById(tableId);
    }
}
