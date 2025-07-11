package com.cat.S5._2.bookstack.security.exceptions;

public class TokenUnsupportedException extends JwtExceptions {
    public TokenUnsupportedException(Throwable cause){
        super ("Unsupported token", cause);
    }
}
