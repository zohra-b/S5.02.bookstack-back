package com.cat.S5._2.bookstack.dtos.book;

import lombok.Builder;

import java.util.List;

@Builder
public record BookCardDto(
        Long   bookId,
        String title,
        String author,
        String imageUrl,
        List<String> genres
) {}
