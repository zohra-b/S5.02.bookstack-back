package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.author.CreateAuthorDto;
import com.cat.S5._2.bookstack.dtos.author.UpdateAuthorDto;
import com.cat.S5._2.bookstack.dtos.genre.CreateGenreDto;
import com.cat.S5._2.bookstack.dtos.genre.GenreDto;
import com.cat.S5._2.bookstack.dtos.genre.UpdateGenreDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto toDto(Genre genre);
    Genre toEntity(GenreDto dto);



    @Mapping(target = "id", ignore = true)
    Genre toEntity(CreateGenreDto dto);


    @Mapping(target = "id", ignore = true)
    Genre toEntity(UpdateGenreDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Genre entity, UpdateGenreDto dto);

    List<GenreDto> toDtoList(List<Genre> genres);
    List<Genre> toEntityList(List<GenreDto> dtos);
}

