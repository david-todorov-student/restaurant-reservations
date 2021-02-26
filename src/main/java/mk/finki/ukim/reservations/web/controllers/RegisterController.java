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

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterChoicePage(Model model) {
        model.addAttribute("bodyContent", "register-choice");
        return "master-template";
    }

    @PostMapping
    public String chooseRegister(@RequestParam String choice,
                                 Model model) {
        if (choice != null) {
            if (choice.toLowerCase().contains("user")) {
                return "redirect:/user";
            } else if (choice.toLowerCase().contains("restaurant")) {
                return "redirect:/restaurants/register";
            } else {
                model.addAttribute("error", "Try again.");
                return "redirect:/";            }
        } else {
            return "redirect:/register?error=You+need+to+choose+a+role";
        }
    }

    @GetMapping("/user")
    public String getRegisterUserPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("bodyContent", "register-user");
        model.addAttribute("error", error);
        return "master-template";
    }

    @PostMapping("/user")
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
            return "redirect:/user?error="+exception.getMessage();
        }
    }

}

