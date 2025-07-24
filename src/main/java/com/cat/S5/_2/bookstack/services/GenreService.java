package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.genre.CreateGenreDto;
import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.dtos.genre.UpdateGenreDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.mappers.GenreMapper;
import com.cat.S5._2.bookstack.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDto> getAll(){
       return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .toList();
    }

    public Optional<Genre> findByName(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }


    public GenreDto createGenre(CreateGenreDto dto) {
        Genre genre = genreMapper.toEntity(dto);
        Genre saved = genreRepository.save(genre);
        return genreMapper.toDto(saved);
    }

    public GenreDto updateGenre(Long id, UpdateGenreDto dto){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty()) return null;

        Genre genre = optionalGenre.get();
        updateIfNotNull(dto.getName(), genre::setName);
        Genre saved = genreRepository.save(genre);
        return genreMapper.toDto(saved);
    }

    private <T> void updateIfNotNull(T newValue, Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }


    public void deleteGenre(Long genreId){
        genreRepository.deleteById(genreId);
    }
}