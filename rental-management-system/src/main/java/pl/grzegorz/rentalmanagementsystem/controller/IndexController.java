package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index"; // This will resolve to "index.html" in the templates directory
    }
}