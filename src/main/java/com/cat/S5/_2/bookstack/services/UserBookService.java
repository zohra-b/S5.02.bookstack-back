package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.userbook.CreateUserBookDto;
import com.cat.S5._2.bookstack.dtos.userbook.UpdateUserBookDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.entities.UserBook;
import com.cat.S5._2.bookstack.enums.BookStatus;
import com.cat.S5._2.bookstack.mappers.UserBookMapper;
import com.cat.S5._2.bookstack.repositories.BookRepository;
import com.cat.S5._2.bookstack.repositories.UserBookRepository;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserBookMapper userBookMapper;

    @Transactional
    public UserBookDto createUserBook(CreateUserBookDto createUserBookDto) {
        User user = userRepository.findById(createUserBookDto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + createUserBookDto.userId()));

        Book book = bookRepository.findById(createUserBookDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + createUserBookDto.bookId()));


        Optional<UserBook> existingAssociation = userBookRepository.findByUserAndBook(user, book);
        if (existingAssociation.isPresent()) {
            throw new IllegalArgumentException("Association for User " + user.getId() + " and Book " + book.getBookId() + " already exists.");
        }

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setStatus(createUserBookDto.status());
        userBook.setRating(createUserBookDto.rating());
        userBook.setComment(createUserBookDto.comment());

        UserBook savedUserBook = userBookRepository.save(userBook);

        return userBookMapper.toDto(savedUserBook);
    }

    @Transactional(readOnly = true)
    public UserBookDto getUserBookById(Long id) {
        UserBook userBook = userBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserBook association not found with ID: " + id));
        return userBookMapper.toDto(userBook);
    }

    @Transactional(readOnly = true)
    public List<UserBookDto> getUserBooksByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        List<UserBook> userBooks = userBookRepository.findByUser(user);
        return userBooks.stream()
                .map(userBookMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserBookDto> getUserBooksByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + bookId));
        List<UserBook> userBooks = userBookRepository.findByBook(book);
        return userBooks.stream()
                .map(userBookMapper::toDto)
                .toList();
    }

    @Transactional
    public UserBookDto updateUserBook(Long id, UpdateUserBookDto updateUserBookDto) {
        UserBook userBook = userBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserBook association not found with ID: " + id));

        if (updateUserBookDto.status() != null) {
            userBook.setStatus(updateUserBookDto.status());
        }
        if (updateUserBookDto.rating() != null) {
            userBook.setRating(updateUserBookDto.rating());
        }

        if (updateUserBookDto.comment() != null) {
            userBook.setComment(updateUserBookDto.comment());
        }

        UserBook updatedUserBook = userBookRepository.save(userBook);
        return userBookMapper.toDto(updatedUserBook);
    }

    @Transactional
    public void deleteUserBook(Long id) {
        if (!userBookRepository.existsById(id)) {
            throw new EntityNotFoundException("UserBook association not found with ID: " + id);
        }
        userBookRepository.deleteById(id);
    }
}