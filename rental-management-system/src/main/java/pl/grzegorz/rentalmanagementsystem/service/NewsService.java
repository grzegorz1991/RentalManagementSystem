package pl.grzegorz.rentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzegorz.rentalmanagementsystem.entity.News;
import pl.grzegorz.rentalmanagementsystem.repository.NewsRepository;

import java.util.List;

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
        // Implementing a custom query method in the repository to fetch the last 3 news
        return newsRepository.findTop3ByOrderByDateDesc();
    }

    public News createOrUpdateNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}