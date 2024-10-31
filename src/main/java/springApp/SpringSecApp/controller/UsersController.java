package springApp.SpringSecApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springApp.SpringSecApp.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "users/addUser")
    public String addUser() {
        return "addUser";
    }

    @GetMapping(value = "users/editUser")
    public String editUser(Model model, @RequestParam("id") int id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/users/addUser")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "addUser";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.delete(userService.findById(id));
        return "redirect:/users";
    }

    @PostMapping(value = "/users/editUser")
    public String editUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "editUser";
        }
        User existingUser = userService.findById(user.getId());
        existingUser = userService.updateUserInfo(user, existingUser);
        userService.update(existingUser);
        return "redirect:/users";
    }
}
