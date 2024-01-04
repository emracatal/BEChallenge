package com.wit.BEChallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommerceErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}