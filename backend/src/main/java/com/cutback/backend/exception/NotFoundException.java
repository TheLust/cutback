package com.cutback.backend.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String entity, String field, String fieldValue) {
        super(String.format("Cannot find [%s] with [%s] = [%s]", entity, field, fieldValue));
    }
}
