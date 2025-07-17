package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.entities.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto toDto(Genre genre);
    Genre toEntity(GenreDto dto);

    List<GenreDto> toDtoList(List<Genre> genres);
    List<Genre> toEntityList(List<GenreDto> dtos);
}

