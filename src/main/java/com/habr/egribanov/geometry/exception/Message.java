package com.habr.egribanov.geometry.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Evgeny Gribanov
 * @version 27.05.2024
 */
@Getter
public enum Message {
    ADDRESS_OUT_OF_DELIVERY_ZONE("Address out of delivery zone", NOT_ACCEPTABLE);

    final String description;
    final HttpStatus httpStatus;

    Message(String description, HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }

}
