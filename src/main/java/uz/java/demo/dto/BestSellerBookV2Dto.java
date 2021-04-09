package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BestSellerBookV2Dto {

    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "book_name")
    private String bookName;
    @Column(name = "comment_count")
    private String commment_count;

}
