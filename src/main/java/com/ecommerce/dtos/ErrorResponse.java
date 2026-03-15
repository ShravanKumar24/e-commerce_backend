package com.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int value, LocalDateTime now) {
        this.message = message;
        this.status = value;
        this.timestamp = now;
    }

}
