package com.mohyehia.ecommerce.advice;

import com.mohyehia.ecommerce.entity.api.ResponseError;
import com.mohyehia.ecommerce.entity.api.ValidationError;
import com.mohyehia.ecommerce.exception.ApiGlobalException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiGlobalException.class)
    public ResponseEntity<ResponseError> handleApiExceptions(ApiGlobalException e, WebRequest request) {
        ResponseError responseError = new ResponseError(e.getMessage(), request.getDescription(false), ExceptionUtils.getStackTrace(e));
        return new ResponseEntity<>(responseError, e.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleAllUncaughtExceptions(RuntimeException e, WebRequest request) {
        ResponseError responseError = new ResponseError(e.getMessage(), request.getDescription(false), ExceptionUtils.getStackTrace(e));
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError = new ValidationError(request.getDescription(false));
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        fieldErrors.forEach(fieldError -> validationError.addError(fieldError.getDefaultMessage()));
        globalErrors.forEach(globalError -> validationError.addError(globalError.getDefaultMessage()));
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}
