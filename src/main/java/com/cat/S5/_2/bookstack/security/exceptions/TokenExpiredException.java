package com.cat.S5._2.bookstack.security.exceptions;

public class TokenExpiredException extends JwtExceptions {
    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(Throwable cause){
        super("Expired token", cause);
    }
}
