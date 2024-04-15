package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.grzegorz.rentalmanagementsystem.entity.News;
import pl.grzegorz.rentalmanagementsystem.service.NewsService;

@Controller
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        model.addAttribute("activePage", "news");

        return "news";
    }

    @GetMapping("/news/{id}")
    public String getNewsById(@PathVariable("id") Long id, Model model) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            return "error";
        }

        model.addAttribute("news", news);
        return "single-news";
    }
}
