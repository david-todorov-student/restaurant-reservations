package mk.finki.ukim.reservations.service;

import mk.finki.ukim.reservations.model.Table;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableService {

    Table addTable(int seats, Long restaurantId);

    Table editTable(Long tableId, int seats, Long restaurantId);

    void deleteTable(Long id);

    List<Table> listAll();
}
