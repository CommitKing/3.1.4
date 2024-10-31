package springApp.SpringSecApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternalController {

    @GetMapping(value = "/")
    public String internalPage() {
        return "index";
    }
}
