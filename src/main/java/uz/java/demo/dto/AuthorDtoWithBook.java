package uz.java.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorDtoWithBook extends AuthorDto {
    private List<BookDto> books;

    public AuthorDtoWithBook(Long id, String name, List<BookDto> books) {
        super(id, name);
        this.books = books;
    }


}
