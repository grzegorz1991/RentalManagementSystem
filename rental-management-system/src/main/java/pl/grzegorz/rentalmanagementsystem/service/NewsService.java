package pl.grzegorz.rentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzegorz.rentalmanagementsystem.entity.News;
import pl.grzegorz.rentalmanagementsystem.repository.NewsRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;

import java.time.YearMonth;


@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> getLast3News() {
        return newsRepository.findTop3ByOrderByDateDesc();
    }

    public List<News> getLast5News() {
        return newsRepository.findTop5ByOrderByDateDesc();
    }

    public News createOrUpdateNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public List<Map<String, Integer>> getArticleCountByMonth() {
        List<Map<String, Integer>> articleCountByMonth = new ArrayList<>();
        List<News> newsList = getAllNews();
        Map<String, Integer> countMap = new HashMap<>();


        for (News news : newsList) {
            // Extract month and year from the news date and format it in English
            String monthYear = news.getDate().format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));

            // Increment the count for the corresponding month-year
            countMap.put(monthYear, countMap.getOrDefault(monthYear, 0) + 1);
        }
        articleCountByMonth.add(countMap);
        return articleCountByMonth;
    }
}