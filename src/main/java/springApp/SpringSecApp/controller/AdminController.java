package springApp.SpringSecApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.service.RoleService;
import springApp.SpringSecApp.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users-panel")
    public String usersPanel(Model model) {
        model.addAttribute("users", userService.findAllUsersWithRoles());
        return "admin/users-panel";
    }

    @GetMapping("/users-panel/add-user")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/add-user";
    }

    @GetMapping("/users-panel/edit-user")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findUserByIdWithRole(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit-user";
    }

    @PostMapping("/users-panel/add-user")
    public String addUser(@ModelAttribute("user") @Valid User user) {
        userService.save(user);
        return "redirect:/admin/users-panel";
    }

    @PostMapping("/users-panel/edit-user")
    public String editUser(@ModelAttribute("user") @Valid User user) {
        userService.save(user);
        return "redirect:/admin/users-panel";
    }

    @PostMapping("/users-panel/delete-user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.delete(userService.findUserByIdWithRole(id));
        return "redirect:/admin/users-panel";
    }
}
