package mk.finki.ukim.reservations.web.controllers;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.enumerations.Role;
import mk.finki.ukim.reservations.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/", "/restaurants"})
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getRestaurantsPage(Model model){
        List<Restaurant> restaurants = this.restaurantService.listAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("bodyContent", "restaurants");
        return "master-template";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register-restaurant");
        return "master-template";
    }

    @PostMapping
    public String registerRestaurant(@RequestParam String name,
                                     @RequestParam String password,
                                     @RequestParam String repeatedPassword,
                                     @RequestParam String address,
                                     @RequestParam String city,
                                     @RequestParam String country,
                                     @RequestParam Double latitude,
                                     @RequestParam Double longitude,
                                     Model model) {
        try {
            this.restaurantService.register(name, password, repeatedPassword, address,
                    city, country, latitude, longitude);
            return "redirect:/login";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent", "register-restaurant");
            return "master-template";
        }
    }

}
