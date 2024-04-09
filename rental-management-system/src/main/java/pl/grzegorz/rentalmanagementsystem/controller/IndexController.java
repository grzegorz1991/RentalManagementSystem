package pl.grzegorz.rentalmanagementsystem.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.grzegorz.rentalmanagementsystem.service.NewsService;
import pl.grzegorz.rentalmanagementsystem.util.Slogans;

import java.util.Random;

@Controller
public class IndexController {
    private final NewsService newsService;

    public IndexController(NewsService newsService) {
        this.newsService = newsService;
    }

    private String getRandomSlogan() {
        Random random = new Random();
        int index = random.nextInt(Slogans.slogans.length);
        return Slogans.slogans[index];
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("randomSlogan", getRandomSlogan());
        model.addAttribute("newsList", newsService.getLast3News());
        return "index";
    }
}