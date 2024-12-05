package springApp.SpringSecApp.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ViewController {

    @GetMapping("/admin")
    public void redirectToAdminPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/admin.html");
    }

    @GetMapping("/user")
    public void redirectToUserPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/user.html");
    }

    @GetMapping("/login")
    public void redirectToLoginPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }
}
