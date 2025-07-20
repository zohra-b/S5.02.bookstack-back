package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.author.CreateAuthorDto;
import com.cat.S5._2.bookstack.dtos.author.UpdateAuthorDto;
import com.cat.S5._2.bookstack.entities.Author;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto dto);

    @Mapping(target = "authorId", ignore = true)
    Author toEntity(CreateAuthorDto createDto);


    @Mapping(target = "authorId", ignore = true)
    Author toEntity(UpdateAuthorDto updateDto);

    @Mapping(target = "authorId", ignore = true)
    void updateEntity(@MappingTarget Author entity, UpdateAuthorDto updateDto);

    default List<AuthorDto> toDtoList(List<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            return Collections.emptyList(); //
        }
        return authors.stream()
                .map(this::toDto)
                .toList();
    }

    default List<Author> toEntityList(List<AuthorDto> authorsDto) {
        if (authorsDto == null || authorsDto.isEmpty()) {
            return Collections.emptyList();
        }
        return authorsDto.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<Author> toEntityListFromCreateDto(List<CreateAuthorDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<Author> toEntityListFromUpdateDto(List<UpdateAuthorDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}

