package com.cat.S5._2.bookstack.repositories;

import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.enums.BookStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
   Optional<Book> findByIsbn(String isbn);
    List<Book> findByPublicationYear(Integer year);
    List<Book> findByTitleStartingWithIgnoreCase(String prefix);

    long countByAuthor(String author);




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


}
