package com.habr.egribanov.geometry.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Evgeny Gribanov
 * @version 27.05.2024
 */
@Getter
public class RestException extends RuntimeException {
    private final HttpStatus httpStatus;

    public RestException(Message exception) {
        super(exception.getDescription());
        this.httpStatus = exception.getHttpStatus();
    }
}
