package springApp.SpringSecApp.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springApp.SpringSecApp.custom_exceptions.InvalidUserDataException;
import springApp.SpringSecApp.dto.UserDTO;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.service.RoleService;
import springApp.SpringSecApp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsersWithRoles();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findUserByIdWithRoles(id);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new InvalidUserDataException("Invalid user data: " + errors);
        }
        userService.save(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> editUser(@PathVariable("id") int id, @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new InvalidUserDataException("Invalid user data: " + errors);
        }
        userService.update(id, userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
