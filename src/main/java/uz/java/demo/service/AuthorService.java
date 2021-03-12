package uz.java.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.java.demo.dto.AuthorDto;
import uz.java.demo.dto.AuthorDtoWithBook;
import uz.java.demo.dto.BookDto;
import uz.java.demo.entity.Author;
import uz.java.demo.entity.Book;
import uz.java.demo.repository.AuthorRepository;
import uz.java.demo.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, ModelMapper mapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(this::convertAuthorToDto).collect(Collectors.toList());
    }


    public AuthorDtoWithBook getAuthorByIdWithBook(Long id) {
        AuthorDtoWithBook author = mapper.map(authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found.")), AuthorDtoWithBook.class);
        List<BookDto> bookDtos = bookRepository.findAllByAuthorId(id).stream().map(this::BookToBookDto).collect(Collectors.toList());
        author.setBooks(bookDtos);
        return author;


    }

    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author author = mapper.map(authorDto, Author.class);
        return mapper.map(authorRepository.save(author), AuthorDto.class);

    }

    private BookDto BookToBookDto(Book book) {
        return mapper.map(book, BookDto.class);
    }

    private AuthorDto convertAuthorToDto(Author author) {
        return mapper.map(author, AuthorDto.class);
    }
}
