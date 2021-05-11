package com.mohyehia.ecommerce.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiGlobalException extends RuntimeException {
    protected ApiGlobalException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
