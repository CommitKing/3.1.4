package springApp.SpringSecApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springApp.SpringSecApp.model.Role;
import springApp.SpringSecApp.model.User;
import springApp.SpringSecApp.service.RoleService;
import springApp.SpringSecApp.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {
      
}
