package com.cat.S5._2.bookstack.dtos.auth;

public record TokenResponse(String tokenResponse, Long userId, String userName, String userRole) {
}
