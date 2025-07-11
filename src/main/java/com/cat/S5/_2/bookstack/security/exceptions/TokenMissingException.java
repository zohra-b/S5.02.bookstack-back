package com.cat.S5._2.bookstack.security.exceptions;

public class TokenMissingException extends JwtExceptions{
    TokenMissingException(){
        super("Token is missing", null);
    }
}
