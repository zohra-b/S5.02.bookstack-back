package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.book.*;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.Genre;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    @Mapping(target = "genres",
            source = "genres",
            qualifiedByName = "genresToList")
    BookDto toBookDto(Book book);


    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    @Mapping(target = "genres",
            source = "genres",
            qualifiedByName = "genresToList")
    BookCardDto toBookCardDto(Book book);

    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    BookSummaryDto toBookSummaryDto(Book book);

    List<BookDto> toBookDto(List<Book> books);
    List<BookCardDto> toBookCardDto(List<Book> books);

    Book toEntity(BookDto bookDto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book toEntity(UpdateBookDto dto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book toEntity(CreateBookDto dto);

    // helper
    @Named("authorsToString")
    static String authorsToString(Set<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            return "Unknown Author";
        }
        return authors.stream()
                .filter(Objects::nonNull)
                .map(a -> {
                    boolean hasFirstName = a.getFirstName() != null;
                    boolean hasLastName = a.getLastName() != null;

                    if (hasFirstName && hasLastName) {
                        return a.getFirstName() + " " + a.getLastName();
                    } else if (hasLastName) {
                        return a.getLastName();
                    } else if (hasFirstName) {
                        return a.getFirstName();
                    }
                    return "Anonymous";
                })
                .collect(Collectors.joining(", "));
    }
    @Named("genresToList")
    static List<String> genresToList(Set<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return List.of();
        }

        return genres.stream()
                .filter(Objects::nonNull)
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

}
