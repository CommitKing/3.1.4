package springApp.SpringSecApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping
    public String login() {
        return "authentication/login";
    }
}
