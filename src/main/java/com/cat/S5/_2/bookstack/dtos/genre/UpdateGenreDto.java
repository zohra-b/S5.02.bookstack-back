package com.cat.S5._2.bookstack.dtos.genre;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateGenreDto {
    private String name;
}