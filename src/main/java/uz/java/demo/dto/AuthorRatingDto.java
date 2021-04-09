package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRatingDto {
    @Id
    private Long id;
    @Column(name = "comment_count")
    private Long commentCount;
    @Column(name = "book_count")
    private Long bookCount;
    @Column(name = "quantity")
    private Long quantity;

}
