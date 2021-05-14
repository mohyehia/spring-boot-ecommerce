package com.mohyehia.ecommerce.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseError {
    private String message;
    private String path;
    private String stackTrace;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ResponseError() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseError(String message, String path, String stackTrace) {
        this();
        this.message = message;
        this.path = path;
        this.stackTrace = stackTrace;
    }
}
