package pl.grzegorz.rentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzegorz.rentalmanagementsystem.entity.Comment;
import pl.grzegorz.rentalmanagementsystem.repository.CommentRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Retrieve all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Retrieve a comment by its ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Update an existing comment
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Delete a comment by its ID
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

//    public List<Comment> getCommentsByNewsId(Long newsId) {
//        List<Comment> comments = commentRepository.findByNewsId(newsId);
//        for (Comment comment : comments) {
//            String formattedDate = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
//
//            comment.setFormattedDate(formattedDate);
//        }
//        return comments;
//    }

    public List<Comment> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findByNewsId(newsId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        for (Comment comment : comments) {
            String formattedDate = comment.getCreatedAt().format(formatter);
            comment.setFormattedDate(formattedDate);
        }
        return comments;
    }

    public Integer numberOfCommentsByNewsId(Long newsId){
        List<Comment> comments = commentRepository.findByNewsId(newsId);
        return comments.size();
    }
}
