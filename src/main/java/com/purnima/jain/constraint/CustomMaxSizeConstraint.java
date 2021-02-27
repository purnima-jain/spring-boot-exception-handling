package com.purnima.jain.constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomMaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomMaxSizeConstraint {

    String message() default "The input list cannot contain more than 3 customers.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}