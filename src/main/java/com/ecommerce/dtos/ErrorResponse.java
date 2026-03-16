package com.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(String message, int value, LocalDateTime now) {
        this.message = message;
        this.status = value;
        this.timestamp = now;
    }

}
