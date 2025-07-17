package com.cat.S5._2.bookstack.mappers;

import com.cat.S5._2.bookstack.dtos.book.*;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.entities.Book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
    BookDto toBookDto(Book book);

    @Mapping(target = "author",
            source = "authors",
            qualifiedByName = "authorsToString")
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
        return authors.stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .collect(Collectors.joining(", "));
    }
}
