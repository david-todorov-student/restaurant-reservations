package mk.finki.ukim.reservations.web.controllers;

import mk.finki.ukim.reservations.model.Reservation;
import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.User;
import mk.finki.ukim.reservations.service.ReservationService;
import mk.finki.ukim.reservations.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RestaurantService restaurantService;

    public ReservationController(ReservationService reservationService, RestaurantService restaurantService) {
        this.reservationService = reservationService;
        this.restaurantService = restaurantService;
    }
//
//    @GetMapping()
//    public String getReservationsByCurrentClient(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//
//        List<Reservation> reservations = this.reservationService.getPlacedReservationsForUser(user);
//
//        if (reservations.isEmpty()) {
//            model.addAttribute("hasError", true);
//            return "redirect:/restaurants?error=NoReservations";
//        }
//
//        model.addAttribute("reservations", reservations);
//        return "userReservations";
//    }
//
//    @GetMapping("/place/{restaurantId}")
//    public String chooseRestaurantDetails(@PathVariable long restaurantId, Model model) {
//
//        if (this.restaurantService.findById(restaurantId).isPresent()) {
//            model.addAttribute("restaurantId", restaurantId);
//            Restaurant restaurant = this.restaurantService.findById(restaurantId).get();
//            model.addAttribute("restaurant", restaurant);
//            return "chooseFromRestaurantMenu";
//        }
//
//        model.addAttribute("hasError", true);
//        return "redirect:/restaurants?error=RestaurantNotFound";
//    }
//
//    @PostMapping("/place/{restaurantId}")
//    public String makeReservations(@PathVariable long restaurantId,
//                            @RequestParam String clientDeliveryAddress, HttpSession session) {
//
//        User user = (User) session.getAttribute("user");
//        List<Reservation> reservations = reservationService.getAllRestaurantsInUserActiveReservations(user.getUsername());
//
//        if (this.restaurantService.findById(restaurantId).isPresent()) {
//            reservationService.placeReservation(user, clientDeliveryAddress, reservations);
//            return "redirect:/restaurants";
//        }
//
//        return "redirect:/restaurants?error=RestaurantNotAvailable";
//    }
}
