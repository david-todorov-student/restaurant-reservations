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
            RestaurantType type = RestaurantType.valueOf(filterBy);
            restaurants = this.restaurantService.filterByType(type);
        } else {
            restaurants = this.restaurantService.listAll();
        }

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("restTypes", RestaurantType.values());
        model.addAttribute("bodyContent", "restaurants");
        model.addAttribute("bodyContent", "restTypes");

        return "master-template";
    }

    @PostMapping("/add")
    public String saveRestaurant(@RequestParam String name,
                              @RequestParam String address,
                              @RequestParam String city,
                              @RequestParam String country,
                              @RequestParam double latitude,
                              @RequestParam double longitude,
                              @RequestParam String type) throws RestaurantNotFoundException {
        RestaurantType restaurantType = RestaurantType.valueOf(type);
        this.restaurantService.save(name, address, city, country, latitude, longitude, type);

        return "redirect:/restaurants";
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
            model.addAttribute("restTypes", RestaurantType.values());
            model.addAttribute("restaurant", restaurant);
            return "add-restaurant";
        }

        return "redirect:/restaurants?error=RestaurantNotFound";
    }

    @GetMapping("/add-form")
    public String addRestaurant(Model model){
        List<Restaurant> restaurants = this.restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("restTypes", RestaurantType.values());

        return "add-restaurant";
    }

    @PostMapping("/searched-restaurants")
    public String findAllByText(@RequestParam String text, Model model) {
        List<Restaurant> restaurants = this.restaurantService.searchByNameOrCity(text);
        model.addAttribute("restaurants", restaurants);

        return "searched-restaurants";
    }
}
