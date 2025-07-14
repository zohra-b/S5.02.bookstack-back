package com.cat.S5._2.bookstack.dtos.book;

import lombok.Builder;

@Builder
public record BookSummaryDto(
        Long bookId,
        String title,
        String author
) {}
