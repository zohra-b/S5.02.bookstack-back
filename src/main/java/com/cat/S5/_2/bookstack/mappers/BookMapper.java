package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.book.BookCardDto;
import com.cat.S5._2.bookstack.dtos.book.BookDto;
import com.cat.S5._2.bookstack.dtos.book.BookSummaryDto;
import com.cat.S5._2.bookstack.dtos.book.CreateBookDto;
import com.cat.S5._2.bookstack.dtos.book.UpdateBookDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.Genre;
import com.cat.S5._2.bookstack.services.GenreService;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    protected GenreService genreService;

    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    @Mapping(target = "genres",
            source = "genres",
            qualifiedByName = "genresToList")
    public abstract BookDto toBookDto(Book book);

    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    @Mapping(target = "genres",
            source = "genres",
            qualifiedByName = "genresToList")
    public abstract BookCardDto toBookCardDto(Book book);

    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    public abstract BookSummaryDto toBookSummaryDto(Book book);

    public abstract List<BookDto> toBookDto(List<Book> books);
    public abstract List<BookCardDto> toBookCardDto(List<Book> books);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genreNamesToEntities")
    public abstract Book toEntity(BookDto bookDto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    public abstract Book toEntity(UpdateBookDto dto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    public abstract Book toEntity(CreateBookDto dto);

    // --- HELPER MAPPINGS --- //

    @Named("authorsToString")
    protected static String authorsToString(Set<Author> authors) {
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
    protected static List<String> genresToList(Set<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return List.of();
        }

        return genres.stream()
                .filter(Objects::nonNull)
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    @Named("genreNamesToEntities")
    protected Set<Genre> genreNamesToEntities(List<String> genreNames) {
        if (genreNames == null || genreNames.isEmpty()) {
            return Set.of();
        }

        return genreNames.stream()
                .map(name -> genreService.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Genre not found: " + name)))
                .collect(Collectors.toSet());
    }
}
