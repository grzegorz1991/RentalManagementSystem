package pl.grzegorz.rentalmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {

    @GetMapping("/contact")
    public String contactUs(Model model) {


        return "contact";

    }
}
