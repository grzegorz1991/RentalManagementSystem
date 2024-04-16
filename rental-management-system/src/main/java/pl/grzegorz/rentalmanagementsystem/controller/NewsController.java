package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.grzegorz.rentalmanagementsystem.entity.News;
import pl.grzegorz.rentalmanagementsystem.service.NewsService;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map;

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
        News newsArticle = newsService.getNewsById(id);
        if (newsArticle == null) {
            return "error";
        }

        model.addAttribute("newsList", newsService.getLast5News());
        model.addAttribute("news", newsArticle);

        List<Map<String, Integer>> articleCountByMonth = newsService.getArticleCountByMonth();
        model.addAttribute("articleCountByMonth", articleCountByMonth);


        // Prepare a map to store articles by month
        Map<String, List<News>> articlesByMonth = new HashMap<>();
        for (News news : newsService.getAllNews()) {
            String monthYear = news.getDate().format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
            articlesByMonth.computeIfAbsent(monthYear, k -> new ArrayList<>()).add(news);
        }
        model.addAttribute("articlesByMonth", articlesByMonth);

        return "single-news";
    }
}
