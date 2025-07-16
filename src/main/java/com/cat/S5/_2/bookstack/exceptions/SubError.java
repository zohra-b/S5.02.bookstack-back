package com.cat.S5._2.bookstack.exceptions;

public record SubError(
        String field,
        String rejectedValue,
        String message
) {
}
