package mk.finki.ukim.reservations.web.controllers;

import mk.finki.ukim.reservations.config.DataHolder;
import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.Table;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.model.exceptions.RestaurantNotFoundException;
import mk.finki.ukim.reservations.model.exceptions.TableNotFoundException;
import mk.finki.ukim.reservations.service.ReservationService;
import mk.finki.ukim.reservations.service.RestaurantService;
import mk.finki.ukim.reservations.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RestaurantService restaurantService;
    private final TableService tableService;

    public ReservationController(ReservationService reservationService, RestaurantService restaurantService, TableService tableService) {
        this.reservationService = reservationService;
        this.restaurantService = restaurantService;
        this.tableService = tableService;
    }

    @GetMapping()
    public String getReservationsByCurrentClient(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        List<Reservation> reservations = this.reservationService.getPlacedReservationsForUser(user);

        if (reservations.isEmpty()) {
            model.addAttribute("hasError", true);
            return "redirect:/restaurants?error=NoReservations";
        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("bodyContent", "userReservations");
        return "master-template";
    }

    @GetMapping("/place/{restaurantId}")
    public String chooseRestaurantDetails(@PathVariable long restaurantId, Model model) {

        if (this.restaurantService.findById(restaurantId).isPresent()) {
            model.addAttribute("restaurantId", restaurantId);
            Restaurant restaurant = this.restaurantService.findById(restaurantId).get();
            model.addAttribute("restaurant", restaurant);

            List<Table> tables = this.tableService.listAll();
            model.addAttribute("tables", tables);

            Reservation reservation = new Reservation();
            model.addAttribute("validFrom", reservation.getValidFrom());
            model.addAttribute("validUntil", reservation.getValidUntil());

            model.addAttribute("bodyContent", "makeReservations");

            return "master-template";
        }

        model.addAttribute("hasError", true);
        return "redirect:/restaurants?error=RestaurantNotFound";
    }

    @PostMapping("/place/{restaurantId}")
    public String makeReservations(@PathVariable long restaurantId,
                                   @RequestParam long tableId,
                                   @RequestParam Date validFrom,
                                   @RequestParam Date validUntil, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Restaurant restaurant = this.restaurantService.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        Table table = this.tableService.findById(tableId).orElseThrow(TableNotFoundException::new);
//        List<Restaurant> restaurants = reservationService.getAllRestaurantsInUserActiveReservations(user.getUsername());

        if (this.restaurantService.findById(restaurantId).isPresent()) {
            reservationService.makeReservation(table, validFrom, validUntil, user, restaurant);
            return "redirect:/restaurants";
        }

        return "redirect:/restaurants?error=RestaurantNotAvailable";
    }
}
