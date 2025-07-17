package com.cat.S5._2.bookstack.dtos.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateBookDto(
        @NotBlank
        @Size(max = 100)
        String title,

        String description,
        Integer publicationYear,
        String language,
        String imageUrl,

        @NotNull
        String isbn,

        @NotNull
        List<Long> authorIds,

        @NotNull
        List<Long> genreIds
) {}

