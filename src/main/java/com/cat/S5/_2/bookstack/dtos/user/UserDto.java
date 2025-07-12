package com.cat.S5._2.bookstack.dtos.user;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String username,
        String email,
        Set<String> roles) {}
