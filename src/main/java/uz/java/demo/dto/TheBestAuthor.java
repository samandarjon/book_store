package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.java.demo.entity.Author;
import uz.java.demo.entity.Comment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheBestAuthor {
    private long booksCount;
    private long commentsCount;
    private Author authors;

}
