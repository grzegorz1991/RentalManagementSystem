package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageNotFoundController {


    @GetMapping("/error404")
    public String pageNotFound() {

        return "404";
    }
}
