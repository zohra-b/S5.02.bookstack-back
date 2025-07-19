package com.cat.S5._2.bookstack.dtos.user;

import com.cat.S5._2.bookstack.enums.UserRole;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long userId,
        String userName,
        String email,
        UserRole role) {}
