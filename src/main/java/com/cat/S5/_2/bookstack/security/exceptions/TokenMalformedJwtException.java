package com.cat.S5._2.bookstack.security.exceptions;

public class TokenMalformedJwtException extends JwtExceptions{
    public TokenMalformedJwtException(Throwable cause){
        super("Token is malformed", cause);
    }
}
