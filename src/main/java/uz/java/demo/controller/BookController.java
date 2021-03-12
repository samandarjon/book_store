package uz.java.demo.controller;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.demo.dto.*;
import uz.java.demo.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    private ResponseEntity<Pageable<BookDtoWithAuthorId>> getAllBooks(@RequestParam(name = "page", defaultValue = "1") Long page) {
        return ResponseEntity.ok(bookService.getAllBook(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookWithAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<BookDtoWithComment> getBookWithComment(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookByIdWithComment(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookDtoWithAuthor>> filterBook(@RequestParam(value = "ISBN_10", defaultValue = "") String ISBN_10, @RequestParam(value = "author", defaultValue = "") String author) {
        return ResponseEntity.ok(bookService.filterBookByAuthorOrISBN10(ISBN_10, author));
    }

    @PostMapping
    private ResponseEntity<BookDtoWithAuthorId> saveBook(@RequestBody BookDtoWithAuthorId book) throws NotFoundException {
        return ResponseEntity.ok(bookService.saveBook(book));
    }
}
