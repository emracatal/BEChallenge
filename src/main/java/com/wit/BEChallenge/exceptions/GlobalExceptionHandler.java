package com.wit.BEChallenge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CommerceErrorResponse> handleException(CommerceException commerceException) {
        CommerceErrorResponse commerceErrorResponse = new CommerceErrorResponse(
                commerceException.getHttpStatus().value(), commerceException.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(commerceErrorResponse, commerceException.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<CommerceErrorResponse> handleException(Exception exception) {
        CommerceErrorResponse commerceErrorResponse = new CommerceErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now()
        );
        log.error(exception.getMessage());
        return new ResponseEntity<>(commerceErrorResponse, HttpStatus.BAD_REQUEST);
    }
}