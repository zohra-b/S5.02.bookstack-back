package com.cat.S5._2.bookstack.dtos.userbook;

import com.cat.S5._2.bookstack.enums.BookStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateUserBookDto(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Book ID is required")
        Long bookId,

        @NotNull(message = "Status is required")
        BookStatus status,

        @Min(value = 0, message = "Rating must be at least 0")
        @Max(value = 5, message = "Rating must be at most 5")
        int rating,

        @Size(max = 1000, message = "Comment must be less than 1000 characters")
        String comment
) {
}