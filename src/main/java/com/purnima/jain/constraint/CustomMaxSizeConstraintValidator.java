package com.purnima.jain.constraint;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.purnima.jain.json.CustomerPostRequestDto;

public class CustomMaxSizeConstraintValidator implements ConstraintValidator<CustomMaxSizeConstraint, List<CustomerPostRequestDto>> {

	@Override
	public boolean isValid(List<CustomerPostRequestDto> values, ConstraintValidatorContext context) {
		return values.size() <= 3;
	}

}