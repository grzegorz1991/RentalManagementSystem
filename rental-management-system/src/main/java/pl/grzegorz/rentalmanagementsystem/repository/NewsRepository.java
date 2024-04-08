package pl.grzegorz.rentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.grzegorz.rentalmanagementsystem.entity.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByDateDesc();
}
