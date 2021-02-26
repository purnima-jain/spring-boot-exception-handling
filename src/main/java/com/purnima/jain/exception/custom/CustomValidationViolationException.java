package com.purnima.jain.exception.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import com.purnima.jain.exception.apierror.CustomValidationError;

public class CustomValidationViolationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private List<CustomValidationError> customValidationErrorList = new ArrayList<>();	
	
	public CustomValidationViolationException(List<CustomValidationError> customValidationErrorList) {
        super(CustomValidationViolationException.generateMessage(customValidationErrorList));
        this.customValidationErrorList = customValidationErrorList;
    }

    private static String generateMessage(List<CustomValidationError> customValidationErrorList) {
        return "Operation could not succeed due to invalid input " +
        		customValidationErrorList;
    }
    
    public List<FieldError> getFieldErrors() {
    	List<FieldError> fieldErrorList = customValidationErrorList.stream().map(customValidationError -> convertCustomValidationErrorToFieldError(customValidationError)).collect(Collectors.toList());
    	return fieldErrorList;
    }
    
    private FieldError convertCustomValidationErrorToFieldError(CustomValidationError customValidationError) {
    	String objectName = customValidationError.getObject();
    	String field = customValidationError.getField();
    	Object rejectedValue = customValidationError.getRejectedValue();
    	String defaultMessage = customValidationError.getMessage();
    	FieldError fieldError = new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    	return fieldError;
    }

}
