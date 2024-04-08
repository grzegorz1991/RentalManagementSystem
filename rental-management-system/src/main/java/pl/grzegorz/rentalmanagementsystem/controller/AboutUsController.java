package pl.grzegorz.rentalmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.grzegorz.rentalmanagementsystem.util.Slogans;

import java.util.Random;

@Controller
public class AboutUsController {

    private String getRandomSlogan() {
        Random random = new Random();
        int index = random.nextInt(Slogans.slogans.length);
        return Slogans.slogans[index];
    }
    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        model.addAttribute("randomSlogan", getRandomSlogan());
        return "about";
    }
}
