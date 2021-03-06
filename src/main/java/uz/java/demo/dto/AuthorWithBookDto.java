package uz.java.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorWithBookDto extends AuthorDto {
    private List<BookDto> books;

    public AuthorWithBookDto(Long id, String name, List<BookDto> books) {
        super(id, name);
        this.books = books;
    }


}
