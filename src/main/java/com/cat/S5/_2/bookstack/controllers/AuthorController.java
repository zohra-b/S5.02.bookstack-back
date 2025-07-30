package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.author.CreateAuthorDto;
import com.cat.S5._2.bookstack.dtos.author.UpdateAuthorDto;
import com.cat.S5._2.bookstack.services.AuthorService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody CreateAuthorDto dto) {
        return ResponseEntity.ok(authorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody UpdateAuthorDto dto) {
        AuthorDto updated = authorService.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok("Author has been successfully deleted");
    }


}

