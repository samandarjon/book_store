package uz.java.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.demo.dto.AuthorDto;
import uz.java.demo.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorWithBook(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorByIdWithBook(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity.ok(authorService.saveAuthor(author));
    }
}
