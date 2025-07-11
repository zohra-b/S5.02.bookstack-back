package com.cat.S5._2.bookstack.security.exceptions;

public class TokenSignatureException extends JwtExceptions{
    public TokenSignatureException (Throwable cause){
        super("The Jwt signature is not valid", cause);
    }
}
