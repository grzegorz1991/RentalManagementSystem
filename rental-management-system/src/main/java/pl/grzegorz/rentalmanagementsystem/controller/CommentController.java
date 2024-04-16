package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.grzegorz.rentalmanagementsystem.dto.CommentDTO;
import pl.grzegorz.rentalmanagementsystem.entity.Comment;
import pl.grzegorz.rentalmanagementsystem.entity.News;
import pl.grzegorz.rentalmanagementsystem.service.CommentService;
import pl.grzegorz.rentalmanagementsystem.service.NewsService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final NewsService newsService;

    public CommentController(CommentService commentService, NewsService newsService) {
        this.commentService = commentService;
        this.newsService = newsService;
    }

    @PostMapping("/submit")
    public String submitComment(@ModelAttribute CommentDTO commentDTO , RedirectAttributes redirectAttributes) {
        // Map DTO fields to Comment entity
        Comment comment = new Comment();
        comment.setUsername(commentDTO.getName());
        comment.setContent(commentDTO.getComment());
        comment.setEmail(commentDTO.getEmail());

        // Get the News object based on the newsID
        News news = newsService.getNewsById(commentDTO.getNewsID());

        // Check if the News object is found
        if (news != null) {
            comment.setNews(news);
        } else {
            // Handle case where News object is not found
            // You can log an error or perform some other action
        }

        // Set creation and update dates to current date and time
        LocalDateTime now = LocalDateTime.now();
        comment.setCreatedAt(now);
        comment.setUpdatedAt(now);

        commentService.createComment(comment);

        // Add the news ID as a path variable for redirection
        redirectAttributes.addAttribute("id", commentDTO.getNewsID());

        // Redirect to a success page or return a success message
        return "redirect:/news/{id}"; // Redirect to the news page after submitting comment
    }
}