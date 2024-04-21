package com.cutback.backend.advice;

import com.cutback.backend.exception.ExceptionResponse;
import com.cutback.backend.exception.NotAuthenticatedException;
import com.cutback.backend.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotAuthenticatedException.class)
    private ResponseEntity<ExceptionResponse> handleException(NotAuthenticatedException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<Map<String, String>> handleException(ValidationException e) {
        return new ResponseEntity<>(
                e.getErrors(),
                HttpStatus.BAD_REQUEST
        );
    }
}
