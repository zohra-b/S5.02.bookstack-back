package com.cat.S5._2.bookstack.dtos.book;

import lombok.Builder;

@Builder
public record BookDto(
        Long bookId,
        String title,
        String author,
        Integer publicationYear,
        String description,
        String imageUrl,
        String isbn
) {}
