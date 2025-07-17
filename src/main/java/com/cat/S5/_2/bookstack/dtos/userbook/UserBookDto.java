package com.cat.S5._2.bookstack.dtos.userbook;

import com.cat.S5._2.bookstack.dtos.book.BookSummaryDto;
import com.cat.S5._2.bookstack.dtos.user.UserSummaryDto;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.BookStatus;
import lombok.Builder;

@Builder
public record UserBookDto(
        Long id,
        UserSummaryDto user,
        BookSummaryDto book,
        BookStatus status,
        int rating,
        String comment
) {
}
