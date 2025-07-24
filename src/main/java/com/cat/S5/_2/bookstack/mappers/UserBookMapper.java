package com.cat.S5._2.bookstack.mappers;


import com.cat.S5._2.bookstack.dtos.book.BookSummaryDto;
import com.cat.S5._2.bookstack.dtos.user.UserSummaryDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.entities.UserBook;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { BookMapper.class, UserMapper.class })
public interface UserBookMapper {



    UserBookDto toDto(UserBook userBook);

}
