package com.wit.BEChallenge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommerceException extends RuntimeException{
    private HttpStatus httpStatus;

    public CommerceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
