package com.mohyehia.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiGlobalException {
    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
