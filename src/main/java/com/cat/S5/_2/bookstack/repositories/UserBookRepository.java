package com.cat.S5._2.bookstack.repositories;

import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.entities.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    List<UserBook> findByUser(User user);
    List<UserBook> findByBook(Book book);

    Optional<UserBook> findByUserAndBook(User user, Book book);
    List<UserBook> findByUserAndBook_TitleContainingIgnoreCase(User user, String title);

}
