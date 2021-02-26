package com.purnima.jain.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.purnima.jain.exception.apierror.ApiError;
import com.purnima.jain.exception.custom.CustomValidationViolationException;
import com.purnima.jain.exception.custom.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
     * Handles ResourceNotFoundException.
     *
     * @param ex the ResourceNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        ex.printStackTrace();
        return buildResponseEntity(apiError);
    }
    
    /**
     * Handles CustomValidationViolationException.
     *
     * @param ex the CustomValidationViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(CustomValidationViolationException.class)
    protected ResponseEntity<Object> handleCustomValidationViolationException(CustomValidationViolationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getFieldErrors());
        ex.printStackTrace();
        return buildResponseEntity(apiError);
    }
    
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
	
}