package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.mappers.GenreMapper;
import com.cat.S5._2.bookstack.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreDto createGenre(GenreDto genreDto) {
        System.out.println("1. DTO received in service: " + genreDto);

        Genre genre = genreMapper.toEntity(genreDto);
        System.out.println("2. Entity before saving: " + genre);

        Genre saved = genreRepository.save(genre);
        System.out.println("3. Entity AFTER saving: " + saved);

        GenreDto created = genreMapper.toDto(saved);
        System.out.println("4. Entity returned : " + created);
        return created;
    }

    public void deleteGenre(Long genreId){
        genreRepository.deleteById(genreId);
    }
}