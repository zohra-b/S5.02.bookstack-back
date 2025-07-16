package com.cat.S5._2.bookstack.dtos.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore les nulls dans le JSON
public record UpdateUserDto(
        String userName,
        String email
) {}
