package uz.java.demo.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.java.demo.dto.*;
import uz.java.demo.entity.Book;
import uz.java.demo.entity.Comment;
import uz.java.demo.repository.AuthorRepository;
import uz.java.demo.repository.BookRepository;
import uz.java.demo.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper mapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    public Pageable<BookWithAuthorIdDto> getAllBook(Long page) {
        page = page < 0 ? 1 : page;
        List<Book> books = bookRepository.findAllBook(10L, (page - 1) * 10);
        long totalBooks = bookRepository.count();
        List<BookWithAuthorIdDto> resBookDtos = books.stream().map(this::convertToBookDto).collect(Collectors.toList());
        return new Pageable<>(resBookDtos, page, totalBooks % 10 == 0 ? totalBooks / 10 : (totalBooks / 10 + 1));
    }

    public BookDto getBookById(Long id) {
//        Example 1
//        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
//        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(() -> new RuntimeException("Not found"));
//        BookDto bookDto = convertToBookDto(book);
//        bookDto.setAuthorName(author.getName());
        return bookRepository.getBookWithJoin(id);
//        return bookDto;


    }

    public BookWithCommentDto getBookByIdWithComment(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        BookWithCommentDto bookWithComment = mapper.map(book, BookWithCommentDto.class);
        List<Comment> bookComments = commentRepository.findAllByBookId(id);
        bookWithComment.setComments(bookComments.stream().map(this::commentTOCommentDto).collect(Collectors.toList()));
        return bookWithComment;
    }

    public List<BookWithAuthorDto> filterBookByAuthorOrISBN10(String isbn_10, String author) {
        if (StringUtils.hasText(isbn_10) && StringUtils.hasText(author))
            return bookRepository.findAllByAuthorNameAndISBN_10(author, isbn_10);
        if (StringUtils.hasText(author))
            return bookRepository.findAllByAuthorName(author);
        if (StringUtils.hasText(isbn_10))
            return bookRepository.findAllByISBN_10(isbn_10);

        return new ArrayList<>();
    }

    public BookWithAuthorIdDto saveBook(BookWithAuthorIdDto book) throws NotFoundException {
        if (!authorRepository.existsById(book.getAuthorId()))
            throw  new NotFoundException("Author not found");
        Book bookEntity = mapper.map(book, Book.class);
        bookRepository.save(bookEntity);
        return mapper.map(bookEntity, BookWithAuthorIdDto.class);
    }
    private BookWithAuthorIdDto convertToBookDto(Book book) {
        return mapper.map(book, BookWithAuthorIdDto.class);
    }

    private CommentDto commentTOCommentDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
