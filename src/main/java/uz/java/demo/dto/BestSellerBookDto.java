package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.java.demo.entity.Book;
import uz.java.demo.utils.Mappers;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSellerBookDto {
    private Long quantity;
    private double price;
    private Long bookId;
    private BookDto book;

    public BestSellerBookDto(Long quantity, double price, Long bookId, Book book) {
        this.quantity = quantity;
        this.price = price;
        this.bookId = bookId;
        this.book = Mappers.bookEntityToBookDto(book);
    }

    public void setBook(Book book) {
        this.book = Mappers.bookEntityToBookDto(book);
    }
}
