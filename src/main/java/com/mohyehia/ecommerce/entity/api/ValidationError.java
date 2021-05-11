package com.mohyehia.ecommerce.entity.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError {
    private String path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<String> errors;

    public ValidationError() {
        timestamp = LocalDateTime.now();
        errors = new ArrayList<>();
    }

    public ValidationError(String path) {
        this();
        this.path = path;
    }

    public void addError(String error) {
        errors.add(error);
    }
}
