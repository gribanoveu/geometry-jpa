package com.habr.egribanov.geometry.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(RestException ex) {
        log.error("Response error with code: {}, and message: {}", ex.getHttpStatus().name(), ex.getMessage());
        var errorResponse = new ErrorResponse(ex.getHttpStatus().getReasonPhrase(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
}