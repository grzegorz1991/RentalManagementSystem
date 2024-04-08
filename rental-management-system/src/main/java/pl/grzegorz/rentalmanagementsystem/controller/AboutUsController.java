package pl.grzegorz.rentalmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AboutUsController {


    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "about";
    }
}
