package springApp.SpringSecApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.service.RoleService;
import springApp.SpringSecApp.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAllUsersWithRoles();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        Set<Role> roles = user.getRoles();

        model.addAttribute("allUsers", allUsers);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
