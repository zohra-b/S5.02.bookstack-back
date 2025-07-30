package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.genre.CreateGenreDto;
import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.dtos.genre.UpdateGenreDto;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.services.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAll());
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody CreateGenreDto dto) {
        GenreDto created = genreService.createGenre(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody UpdateGenreDto dto) {
        GenreDto created = genreService.updateGenre(id, dto);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
