package com.cat.S5._2.bookstack.dtos.userbook;

import com.cat.S5._2.bookstack.dtos.book.BookSummaryDto;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.BookStatus;
import lombok.Builder;

@Builder
public record UserBookDto(
        User user,
        BookSummaryDto book,
        BookStatus status,
        int rating
) {
}
