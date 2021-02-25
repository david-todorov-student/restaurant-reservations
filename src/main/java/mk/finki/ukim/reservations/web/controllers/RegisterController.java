package mk.finki.ukim.reservations.web.controllers;

import mk.finki.ukim.reservations.model.enumerations.Role;
import mk.finki.ukim.reservations.service.AuthService;
import mk.finki.ukim.reservations.service.RestaurantService;
import mk.finki.ukim.reservations.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService, RestaurantService restaurantService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register-choice");
        return "master-template";
    }

    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repeatedPassword,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam Role role,
                               Model model) {
        try {
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent", "register-user");
            return "master-template";        }
    }

}

