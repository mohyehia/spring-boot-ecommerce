package com.mohyehia.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiGlobalException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
