package com.cat.S5._2.bookstack.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<SubError> details

){

    public static ErrorResponse create(HttpStatus status, String msg, String path){
        return ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(msg)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}


