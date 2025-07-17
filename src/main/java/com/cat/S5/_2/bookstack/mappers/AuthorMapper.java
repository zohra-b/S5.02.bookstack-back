package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto dto);
}

