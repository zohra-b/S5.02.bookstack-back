package com.cat.S5._2.bookstack.dtos.userbook;

import com.cat.S5._2.bookstack.enums.BookStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateUserBookDto(
        BookStatus status,

        @Min(value = 0, message = "Rating must be at least 0")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,

        @Size(max = 1000, message = "Comment must be less than 1000 characters")
        String comment
) {
}
