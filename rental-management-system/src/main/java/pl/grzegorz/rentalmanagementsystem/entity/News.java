package pl.grzegorz.rentalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate; // Importing LocalDate for date representation

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String author;

    private String title;

    @Lob
    private String excerpt;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    private String imageUrl;
}
