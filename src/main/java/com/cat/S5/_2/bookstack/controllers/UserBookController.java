package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.userbook.CreateUserBookDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.services.UserBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-books")
@RequiredArgsConstructor //Génère un constructeur avec les dépendances nécessaires (injection de UserBookService)
public class UserBookController {
    private final UserBookService userBookService;

    @PostMapping()
    public ResponseEntity<UserBookDto> createUserBook(@Valid @RequestBody CreateUserBookDto createUserBookDto){
        UserBookDto createdUserBook = userBookService.createUserBook(createUserBookDto);
        return new ResponseEntity<>(createdUserBook, HttpStatus.CREATED);
    }

    @GetMapping
}
