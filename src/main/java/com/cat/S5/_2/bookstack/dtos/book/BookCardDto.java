package com.cat.S5._2.bookstack.dtos.book;

import lombok.Builder;

@Builder
public record BookCardDto(
        Long   bookId,
        String title,
        String author,
        String imageUrl
) {}
