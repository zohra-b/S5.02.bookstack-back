package com.cat.S5._2.bookstack.repositories;

import com.cat.S5._2.bookstack.dtos.book.BookCardDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.enums.BookStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findDistinctByAuthorsIn(Collection<Author> authors);
    List<Book> findByAuthorsContains(Author author);
    List<Book> findByLanguageIgnoreCase(String language);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByPublicationYear(Integer year);
    List<Book> findByTitleStartingWithIgnoreCase(String prefix);
    long countByAuthorsContains(Author author);

    @Query("""
            SELECT DISTINCT b FROM Book b
            JOIN b.authors a
            WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(a.lastName)  LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Book> searchByAuthorName(@Param("keyword") String keyword);

    @Query("""
            SELECT COUNT(DISTINCT b) FROM Book b
            JOIN b.authors a
            WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(a.lastName)  LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    long countByAuthorName(@Param("keyword") String keyword);


    @Query("""
                SELECT new BookCardDto(
                    b.bookId,
                    b.title,
                    GROUP_CONCAT(CONCAT(a.firstName, ' ', a.lastName)),
                    b.imageUrl)
                FROM Book b
                LEFT JOIN b.authors a
                GROUP BY b.bookId
            """)
    List<BookCardDto> findAllCards();
}

//    ***** SAVE *****
//    <S extends Book> S save(S entity);          // Saves one book
//    <S extends Book> List<S> saveAll(Iterable<S> entities); // Saves multiple
//    ***** FIND *****
//    Optional<Book> findById(Long id);           // Finds by primary key
//    List<Book> findAll();                       // Gets all books
//    List<Book> findAllById(Iterable<Long> ids); // Finds multiple by IDs
//    Book getOne(Long id);                       // Lazy-loading version
//    Book getReferenceById(Long id);
//    ***** EXISTS *****
//    boolean existsById(Long id);                 // Checks if book exists
//    long count();                               // Counts all books
//    ***** DELETE *****
// void deleteById(Long id);                   // Deletes by ID
// void delete(Book entity);                   // Deletes one book
// void deleteAllById(Iterable<Long> ids);     // Batch delete by IDs
// void deleteAll(Iterable<? extends Book> entities); // Deletes multiple
// void deleteAll();                           // Deletes ALL books



