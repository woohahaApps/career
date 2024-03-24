package com.woohahaapps.career.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException e) {
        final ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    protected ResponseEntity<ErrorResponse> handleNotAuthorizedException(NotAuthorizedException e) {
        final ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.UNAUTHORIZED, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(CouldNotProcessException.class)
    protected ResponseEntity<ErrorResponse> handleCouldNotProcessException(CouldNotProcessException e) {
        final ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyItemException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateKeyItemException(DuplicateKeyItemException e) {
        final ErrorResponse errorResponse = ErrorResponse.create(e, HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
