package com.cat.S5._2.bookstack.dtos.book;

import lombok.Builder;

import java.util.List;

@Builder
public record BookDto(
        Long bookId,
        String title,
        String author,
        Integer publicationYear,
        String description,
        String imageUrl,
        String isbn,
        List<String> genres

) {}
