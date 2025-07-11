package com.cat.S5._2.bookstack.security.exceptions;

public class JwtExceptions extends RuntimeException {
    public JwtExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
