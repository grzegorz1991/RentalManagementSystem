package pl.grzegorz.rentalmanagementsystem.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private long newsID;
    private String name;
    private String email;
    private String comment;
}