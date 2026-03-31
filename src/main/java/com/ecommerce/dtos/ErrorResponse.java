package com.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {

    private String message;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(String message, int value, LocalDateTime now) {
        this.message = message;
        this.status = value;
        this.timestamp = now;
    }

}
