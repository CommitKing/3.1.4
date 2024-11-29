package springApp.SpringSecApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private static final String redirectUrl = "redirect:/admin/admin-panel";

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin-panel")
    public String usersPanel(Model model, Authentication auth) {
        model.addAttribute("users", userService.findAllUsersWithRoles());
        model.addAttribute("user", userService.findByUsername(auth.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("editUser", new User());
        return "admin/admin-panel";
    }

    @PostMapping("/admin-panel/add-user")
    public String addUser(@ModelAttribute("newUser") @Valid User user, BindingResult result, Model model, Authentication auth) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAllUsersWithRoles());
            model.addAttribute("user", userService.findByUsername(auth.getName()));
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("newUser", user);
            model.addAttribute("editUser", new User());
            return "admin/admin-panel";
        }
        userService.save(user);
        return redirectUrl;
    }

    @PostMapping("/admin-panel/edit-user")
    public String editUser(@ModelAttribute("editUser") @Valid User user, BindingResult result, Model model, Authentication auth) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAllUsersWithRoles());
            model.addAttribute("user", userService.findByUsername(auth.getName()));
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("newUser", new User());
            model.addAttribute("editUser", user);
            return "admin/admin-panel";
        }
        userService.save(user);
        return redirectUrl;
    }

    @PostMapping("/admin-panel/delete-user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.delete(id);
        return redirectUrl;
    }
}
