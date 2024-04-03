package pl.grzegorz.rentalmanagementsystem.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class IndexController {

    // Array of slogans
    private static final String[] slogans = {
            "Elevate Your Event with Our Stage Rentals",
            "Stage Your Success with Our Equipment Rentals",
            "Step Onto Greatness: Stage Rentals Made Easy",
            "Lights, Camera, Action! Rent Your Stage Today",
            "Setting the Stage for Your Success",
            "Where Every Event Begins: Our Stage Rentals",
            "From Setup to Spotlight: Your Stage Rental Solution",
            "Rent Your Way to the Spotlight with Our Stage Equipment",
            "Bringing Your Vision to Life, One Stage Rental at a Time",
            "Rent the Stage, Steal the Show!"
    };

    // Method to get a random slogan
    private String getRandomSlogan() {
        Random random = new Random();
        int index = random.nextInt(slogans.length);
        return slogans[index];
    }

    // Controller method to handle requests to the homepage
    @GetMapping("/")
    public String home(Model model) {
        // Add random slogan to the model
        model.addAttribute("randomSlogan", getRandomSlogan());
        return "index"; // returns the name of the Thymeleaf template
    }
}