package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.userbook.CreateUserBookDto;
import com.cat.S5._2.bookstack.dtos.userbook.UpdateUserBookDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.services.UserBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-books")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBookService userBookService;

    @PostMapping
    public ResponseEntity<UserBookDto> createUserBook(@Valid @RequestBody CreateUserBookDto createUserBookDto) {
        UserBookDto createdUserBook = userBookService.createUserBook(createUserBookDto);
        return new ResponseEntity<>(createdUserBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookDto> getUserBookById(@PathVariable Long id) {
        UserBookDto userBook = userBookService.getUserBookById(id);
        return ResponseEntity.ok(userBook);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<UserBookDto>> getUserBooksByUserId(@PathVariable Long userId) {
        List<UserBookDto> userBooks = userBookService.getUserBooksByUserId(userId);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/by-user/{userId}/search")
    public ResponseEntity<List<UserBookDto>> searchUserBooksByUserIdAndKeyword(
            @PathVariable Long userId,
            @RequestParam String keyword) {

        List<UserBookDto> results = userBookService.searchUserBooksByUserIdAndKeyword(userId, keyword);
        return ResponseEntity.ok(results);
    }


    @GetMapping("/by-book/{bookId}")
    public ResponseEntity<List<UserBookDto>> getUserBooksByBookId(@PathVariable Long bookId) {
        List<UserBookDto> userBooks = userBookService.getUserBooksByBookId(bookId);
        return ResponseEntity.ok(userBooks);
    }

    //@PreAuthorize("@userBookRepository.findById(#id).orElseThrow().user.id == authentication.principal.id")
    @PatchMapping("/{id}")
    public ResponseEntity<UserBookDto> updateUserBook(@PathVariable Long id, @Valid @RequestBody UpdateUserBookDto updateUserBookDto) {

        UserBookDto updatedUserBook = userBookService.updateUserBook(id, updateUserBookDto);
        return ResponseEntity.ok(updatedUserBook);
    }

    //@PreAuthorize("@userBookRepository.findById(#id).orElseThrow().user.id == authentication.principal.id or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserBook(@PathVariable Long id) {
        String bookTitle = userBookService.getUserBookById(id).book().title();
        userBookService.deleteUserBook(id);
        return ResponseEntity.ok(  bookTitle + " has succesfully been removed from your list") ;
    }
}
