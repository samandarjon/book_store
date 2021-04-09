package uz.java.demo.controller;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.demo.dao.BookDao;
import uz.java.demo.dto.*;
import uz.java.demo.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookDao bookDao;

    public BookController(BookService bookService, BookDao bookDao) {
        this.bookService = bookService;
        this.bookDao = bookDao;
    }

    @GetMapping()
    private ResponseEntity<Pageable<BookWithAuthorIdDto>> getAllBooks(@RequestParam(name = "page", defaultValue = "1") Long page) {
        return ResponseEntity.ok(bookService.getAllBook(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookWithAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<BookWithCommentDto> getBookWithComment(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookByIdWithComment(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookWithAuthorDto>> filterBook(@RequestParam(value = "ISBN_10", defaultValue = "") String ISBN_10, @RequestParam(value = "author", defaultValue = "") String author) {
        return ResponseEntity.ok(bookService.filterBookByAuthorOrISBN10(ISBN_10, author));
    }

    @PostMapping
    private ResponseEntity<BookWithAuthorIdDto> saveBook(@RequestBody BookWithAuthorIdDto book) throws NotFoundException {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/test")
    public List<?> getAllBestSellerBook() {
        return bookDao.bestSellerBooks();
    }

    @GetMapping("/test/v2")
    public List<?> getAllBestSellerBookV2() {
        return bookDao.bestSellerBooksV2();
    }

    @GetMapping("/authorRating")
    public List<?> getTheBestAuthor() {
        return bookDao.authorRating();
    }

    @GetMapping("/userRating")
    public List<?> getUserRating() {
        return bookDao.getUserRating();
    }
}
