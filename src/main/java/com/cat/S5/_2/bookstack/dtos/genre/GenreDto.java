package com.cat.S5._2.bookstack.dtos.genre;

import jakarta.validation.constraints.NotBlank;

public record GenreDto(Long id, @NotBlank String name) {}

