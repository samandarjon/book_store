package uz.java.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.java.demo.entity.Author;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookDtoWithAuthor extends BookDto {
    private AuthorDto authorDto;

    public BookDtoWithAuthor(Author authorDto,
                             Long id, String name, Double price,
                             Double rate, Long categoryId, String filePath,
                             String ISBN_10, String description, Long languageId) {
        super(id, name, price, rate, categoryId, filePath, ISBN_10, description, languageId);
        this.authorDto = new AuthorDto(authorDto.getId(), authorDto.getName());

    }


}
