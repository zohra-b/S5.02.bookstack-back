package com.cat.S5._2.bookstack.dtos.author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UpdateAuthorDto{
    private String firstName;
    private String lastName;
}
