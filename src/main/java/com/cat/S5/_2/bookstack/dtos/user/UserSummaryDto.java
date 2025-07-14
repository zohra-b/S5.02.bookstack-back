package com.cat.S5._2.bookstack.dtos.user;

import lombok.Builder;

@Builder
public record UserSummaryDto(
        Long userId,
        String userName
) {}
