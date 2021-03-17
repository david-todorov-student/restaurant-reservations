package mk.finki.ukim.reservations.web.controllers;

import mk.finki.ukim.reservations.model.Restaurant;
import mk.finki.ukim.reservations.model.enumerations.Role;
import mk.finki.ukim.reservations.model.exceptions.RestaurantNotFoundException;
import mk.finki.ukim.reservations.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/", "/restaurants"})
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getRestaurantsPage(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String filterBy,
                                     Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Restaurant> restaurants;

        if (filterBy != null && !filterBy.isEmpty()) {
            restaurants = this.restaurantService.filterByText(filterBy);
        } else {
            restaurants = this.restaurantService.listAll();
        }

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("bodyContent", "restaurants");

        return "master-template";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable long id){
        this.restaurantService.deleteById(id);

        return "redirect:/restaurants";
    }

    @GetMapping("/edit/{id}")
    public String editRestaurant(@PathVariable long id, Model model){
        if(this.restaurantService.findById(id).isPresent()){
            Restaurant restaurant = this.restaurantService.findById(id).get();
            model.addAttribute("restaurant", restaurant);
            return "add-restaurant";
        }

        return "redirect:/restaurants?error=RestaurantNotFound";
    }

    @PostMapping("/searched-restaurants")
    public String findAllByText(@RequestParam String text, Model model) {
        List<Restaurant> restaurants = this.restaurantService.filterByText(text);
        model.addAttribute("restaurants", restaurants);

        return "searched-restaurants";
    }
}
