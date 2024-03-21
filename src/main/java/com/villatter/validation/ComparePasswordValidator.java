package com.villatter.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, Object> {

    private String label;

    @Override
    public void initialize(ComparePassword constraintAnnotation) {
        this.label = constraintAnnotation.label();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        var password = new BeanWrapperImpl(value).getPropertyValue("password").toString();
        var passwordConfirmation = new BeanWrapperImpl(value).getPropertyValue("passwordConfirmation").toString();
        return password.equals(passwordConfirmation);
    }
}
