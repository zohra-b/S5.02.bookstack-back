package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.book.BookCardDto;
import com.cat.S5._2.bookstack.dtos.book.BookDto;
import com.cat.S5._2.bookstack.dtos.book.CreateBookDto;
import com.cat.S5._2.bookstack.dtos.book.UpdateBookDto;
import com.cat.S5._2.bookstack.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/cards")
    public ResponseEntity<List<BookCardDto>> getAllBookCards() {
        return ResponseEntity.ok(bookService.getAllBookCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.ok(bookService.createBook(createBookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookDto updateBookDto
    ) {
        return ResponseEntity.ok(bookService.updateBook(id, updateBookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookCardDto>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }

}
