package com.cutback.backend.exception;

public class AuthenticationException extends RuntimeException {

    public static final String BAD_CREDENTIALS = "BAD_CREDENTIALS";

    public AuthenticationException(String message) {
        super(message);
    }
}
