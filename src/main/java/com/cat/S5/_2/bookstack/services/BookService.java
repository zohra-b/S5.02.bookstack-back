package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.book.*;
import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.mappers.AuthorMapper;
import com.cat.S5._2.bookstack.mappers.BookMapper;
import com.cat.S5._2.bookstack.mappers.GenreMapper;
import com.cat.S5._2.bookstack.repositories.AuthorRepository;
import com.cat.S5._2.bookstack.repositories.BookRepository;
import com.cat.S5._2.bookstack.repositories.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toBookDto(books);
    }

    public List<BookCardDto> getAllBookCards() {
        List<Book> books = bookRepository.findAllWithAuthors();
        return bookMapper.toBookCardDto(books);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.toBookDto(book);
    }

    public BookDto createBook(CreateBookDto dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setDescription(dto.description());
        book.setPublicationYear(dto.publicationYear());
        book.setLanguage(dto.language());
        book.setImageUrl(dto.imageUrl());
        book.setIsbn(dto.isbn());

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.authorIds()));
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(dto.genreIds()));
        book.setAuthors(authors);
        book.setGenres(genres);

        book = bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    public BookDto updateBook(Long id, UpdateBookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        updateIfNotNull(dto.title(), book::setTitle);
        updateIfNotNull(dto.description(), book::setDescription);
        updateIfNotNull(dto.publicationYear(), book::setPublicationYear);
        updateIfNotNull(dto.language(), book::setLanguage);
        updateIfNotNull(dto.imageUrl(), book::setImageUrl);
        updateIfNotNull(dto.isbn(), book::setIsbn);

        if (dto.authorIds() != null) {
            validateIdsExist(authorRepository, dto.authorIds(), "Author");
            updateAuthors(book, dto.authorIds());
        }

        if (dto.genreIds() != null) {
            validateIdsExist(genreRepository, dto.genreIds(), "Genre");
            updateGenres(book, dto.genreIds());
        }

        Book updated = bookRepository.save(book);
        return bookMapper.toBookDto(updated);
    }

    @Transactional(readOnly = true)
    public BookSummaryDto getBookSummary(Long bookId) {
        Book book = bookRepository.findWithAuthorsByBookId(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Vérification de débogage
        System.out.println("Authors loaded: " + book.getAuthors());
        System.out.println("Authors size: " + (book.getAuthors() != null ? book.getAuthors().size() : 0));

        return bookMapper.toBookSummaryDto(book);
    }

    // ---UPDATE HELPERS -- //
    private <T> void updateIfNotNull(T newValue, Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
    private void updateAuthors(Book book, List<Long> authorIds) {
        Set<Author> authors = authorIds.isEmpty()
                ? new HashSet<>()
                : new HashSet<>(authorRepository.findAllById(authorIds));
        book.setAuthors(authors);
    }

    private void updateGenres(Book book, List<Long> genreIds) {
        Set<Genre> genres = genreIds.isEmpty()
                ? new HashSet<>()
                : new HashSet<>(genreRepository.findAllById(genreIds));
        book.setGenres(genres);
    }

    private void validateIdsExist(JpaRepository<?, Long> repository, List<Long> ids, String entityName) {
        if (ids != null && !ids.isEmpty()) {
            if (ids != null) {
                List<Long> missingIds = ids.stream()
                        .filter(id -> !repository.existsById(id))
                        .toList();

                if (!missingIds.isEmpty()) {
                    throw new EntityNotFoundException(
                            String.format("%s(s) non trouvé(s) avec IDs : %s",
                                    entityName, missingIds));
                }
            }
        }
    }
    /// ---HELPERS FIN ---///

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

    public List<BookCardDto> searchBooks(String keyword) {
        Set<Book> result = new HashSet<>();
        result.addAll(bookRepository.findByTitleContainingIgnoreCase(keyword));
        result.addAll(bookRepository.searchByAuthorName(keyword));
        return bookMapper.toBookCardDto(List.copyOf(result));
    }

    public List<GenreDto> getGenres(){
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toDtoList(genres);
    }

    public List<AuthorDto> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtoList(authors);
    }


}
