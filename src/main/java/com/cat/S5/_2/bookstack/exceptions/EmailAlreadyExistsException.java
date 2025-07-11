package com.cat.S5._2.bookstack.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }

    public EmailAlreadyExistsException(String email, Throwable cause) {
        super("Email already exists: " + email, cause);
    }
}
