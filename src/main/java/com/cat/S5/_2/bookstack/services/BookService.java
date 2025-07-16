package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.book.BookCardDto;
import com.cat.S5._2.bookstack.dtos.book.BookDto;
import com.cat.S5._2.bookstack.dtos.book.UpdateBookDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.mappers.BookMapper;
import com.cat.S5._2.bookstack.repositories.AuthorRepository;
import com.cat.S5._2.bookstack.repositories.BookRepository;
import com.cat.S5._2.bookstack.repositories.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toBookDto(books);
    }

    public List<BookCardDto> getAllBookCards() {
        return bookRepository.findAllCards();
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.toBookDto(book);
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    public BookDto updateBook(Long id, UpdateBookDto dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(dto.title());
        existingBook.setDescription(dto.description());
        existingBook.setPublicationYear(dto.publicationYear());
        existingBook.setLanguage(dto.language());
        existingBook.setImageUrl(dto.imageUrl());
        existingBook.setIsbn(dto.isbn());

        if (dto.authorIds() != null) {
            Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.authorIds()));
            existingBook.setAuthors(authors);
        }

        if (dto.genreIds() != null) {
            Set<Genre> genres = new HashSet<>(genreRepository.findAllById(dto.genreIds()));
            existingBook.setGenres(genres);
        }

        Book updated = bookRepository.save(existingBook);
        return bookMapper.toBookDto(updated);
    }
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookDto> searchByTitle(String keyword) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        return bookMapper.toBookDto(books);
    }

    public List<BookDto> searchByAuthorName(String keyword) {
        List<Book> books = bookRepository.searchByAuthorName(keyword);
        return bookMapper.toBookDto(books);
    }


}
