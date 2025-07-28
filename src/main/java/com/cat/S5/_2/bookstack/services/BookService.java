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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<BookDto> getAllBooks() {
        logger.debug("Fetching all bookDtos");
        List<Book> books = bookRepository.findAll();
        logger.debug("Number of books fetched : {}" , books.size()  );
        return bookMapper.toBookDto(books);
    }

    public List<BookCardDto> getAllBookCards() {
        logger.debug("Fetching all bookCardDtos");
        List<Book> books = bookRepository.findAllWithAuthors();
        return bookMapper.toBookCardDto(books);
    }

    public BookDto getBookById(Long id) {
        logger.debug("Fetching book with id: {}", id );
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> {
                        logger.error("Book not found with id: {}", id);
                        return new EntityNotFoundException("Book not found with id: " + id);
                });
        logger.debug("Book found: {}", book.getTitle());
        return bookMapper.toBookDto(book);
    }

    public BookDto createBook(CreateBookDto dto) {
        logger.debug("Creating book with title: {}", dto.title());
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

        Book savedBook = bookRepository.save(book);
        logger.info("Book created with ID: {}", savedBook.getBookId());
        return bookMapper.toBookDto(book);
    }

//    public BookDto updateBook(Long id, UpdateBookDto dto) {
//        logger.info("Updating book with id: {} and new title: {}", id, dto.title());
//
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> {
//                    logger.error("Book not found with id: {}", id);
//                    return new EntityNotFoundException("Book not found with id: " + id);
//                });
//
//        updateIfNotNull(dto.title(), book::setTitle);
//        updateIfNotNull(dto.description(), book::setDescription);
//        updateIfNotNull(dto.publicationYear(), book::setPublicationYear);
//        updateIfNotNull(dto.language(), book::setLanguage);
//        updateIfNotNull(dto.imageUrl(), book::setImageUrl);
//        updateIfNotNull(dto.isbn(), book::setIsbn);
//
//        if (dto.authorIds() != null) {
//            logger.debug("Updating authors to IDs: {}", dto.authorIds());
//            validateIdsExist(authorRepository, dto.authorIds(), "Author");
//            updateAuthors(book, dto.authorIds());
//        }
//
//        if (dto.genreIds() != null) {
//            logger.debug("Updating genres to IDs: {}", dto.genreIds());
//            validateIdsExist(genreRepository, dto.genreIds(), "Genre");
//            updateGenres(book, dto.genreIds());
//        }
//
//        bookMapper.updateEntityFromDto(dto, book);
//
//        Book updated = bookRepository.save(book);
//        logger.info("Book updated successfully with id: {}", updated.getBookId());
//
//        return bookMapper.toBookDto(updated);
//    }

public BookDto updateBook(Long id, UpdateBookDto dto) {
        logger.debug("Trying to update book with id {}",id);
    Book book = bookRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Book not found with id: {}", id);
                return new EntityNotFoundException("Book not found with id: " + id);
            });

    if (dto.authorIds() != null) {
        logger.debug("Updating authors with IDs: {}", dto.authorIds());
        validateIdsExist(authorRepository, dto.authorIds(), "Author");
        updateAuthors(book, dto.authorIds());
    }

    if (dto.genreIds() != null) {
        logger.debug("Updating genres with IDs: {}", dto.genreIds());
        validateIdsExist(genreRepository, dto.genreIds(), "Genre");
        updateGenres(book, dto.genreIds());
    }

    try {
        Book updated = bookRepository.save(book);
        logger.info("Book with id {} successfully updated", id);
        return bookMapper.toBookDto(updated);
    } catch (Exception e) {
        logger.error("Failed to update book with id {}: {}", id, e.getMessage(), e);
        throw e;
    }

}

    @Transactional(readOnly = true)
    public BookSummaryDto getBookSummary(Long id) {
        Book book = bookRepository.findWithAuthorsByBookId(id)
                .orElseThrow(() -> {
                    logger.error("Book not found with id: {}", id);
                    return new EntityNotFoundException("Book not found with id: " + id);
                });
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

//    private void updateGenres(Book book, List<Long> genreIds) {
//        Set<Genre> genres = genreIds.isEmpty()
//                ? new HashSet<>()
//                : new HashSet<>(genreRepository.findAllById(genreIds));
//        book.setGenres(genres);
//    }

    private void updateGenres(Book book, List<Long> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            book.setGenres(new HashSet<>());
            return;
        }

        // Filtrer les null pour éviter l'erreur
        List<Long> filteredGenreIds = genreIds.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Valider que tous les IDs existent
        validateIdsExist(genreRepository, filteredGenreIds, "Genre");

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(filteredGenreIds));
        book.setGenres(genres);
    }
//    private void validateIdsExist(JpaRepository<?, Long> repository, List<Long> ids, String entityName) {
//        if (ids != null && !ids.isEmpty()) {
//            if (ids != null) {
//                List<Long> missingIds = ids.stream()
//                        .filter(id -> !repository.existsById(id))
//                        .toList();
//
//                if (!missingIds.isEmpty()) {
//                    throw new EntityNotFoundException(
//                            String.format("%s(s) non trouvé(s) avec IDs : %s",
//                                    entityName, missingIds));
//                }
//            }
//        }
//    }
private void validateIdsExist(JpaRepository<?, Long> repository, List<Long> ids, String entityName) {
    if (ids != null && !ids.isEmpty()) {
        List<Long> missingIds = ids.stream()
                .filter(id -> !repository.existsById(id))
                .toList();

        if (!missingIds.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("%s(s) non trouvé(s) avec IDs : %s", entityName, missingIds));
        }
    }
}
    /// ---HELPERS FIN ---///

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
                logger.error("Book not found with id: {}", id);
                throw new EntityNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookDto> searchByTitle(String keyword) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        return bookMapper.toBookDto(books);
    }


    public List<BookCardDto> searchBooks(String keyword) {
        Set<Book> result = new HashSet<>();

        List<Book> byTitle = bookRepository.findByTitleContainingIgnoreCase(keyword);
        List<Book> byAuthor = bookRepository.searchByAuthorName(keyword);

        result.addAll(byTitle);
        result.addAll(byAuthor);

        logger.debug("Found {} book(s) by title, {} by author, total: {}",
                byTitle.size(), byAuthor.size(), result.size());
        return bookMapper.toBookCardDto(List.copyOf(result));
    }

    public List<GenreDto> getGenres(){
        List<Genre> genres = genreRepository.findAll();
        logger.debug("Fetched {} genres", genres.size());
        return genreMapper.toDtoList(genres);
    }

    public List<AuthorDto> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        logger.debug("Fetched {} authors", authors.size());
        return authorMapper.toDtoList(authors);
    }


}
