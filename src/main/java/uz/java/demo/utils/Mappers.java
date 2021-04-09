package uz.java.demo.utils;

import org.modelmapper.ModelMapper;
import uz.java.demo.dto.BookDto;
import uz.java.demo.entity.Book;

public class Mappers {
    public static BookDto bookEntityToBookDto(Book book) {
        return new ModelMapper().map(book, BookDto.class);
    }


}
