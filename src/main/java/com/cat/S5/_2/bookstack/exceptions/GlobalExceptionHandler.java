package com.cat.S5._2.bookstack.exceptions;

import com.cat.S5._2.bookstack.enums.UserRole;
import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleRoleEnumConversionError(MethodArgumentTypeMismatchException ex, WebRequest request){
        String allowedRoles = Arrays.stream(UserRole.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msg = "Invalid role. Allowed values: " + allowedRoles;
        String path = request.getDescription(false).replace("uri=", "");
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.create(status, msg, path));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Entity not found";
        String path = request.getDescription(false).replace("uri=", "");

        return ResponseEntity
                .status(status)
                .body(ErrorResponse.create(status, msg, path));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getDescription(false).replace("uri=", "");

        return ResponseEntity
                .status(status)
                .body(ErrorResponse.create(status, ex.getMessage(), path));
    }
}
