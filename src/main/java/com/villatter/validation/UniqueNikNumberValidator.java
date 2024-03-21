package com.villatter.validation;

import com.villatter.service.PendudukService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueNikNumberValidator implements ConstraintValidator<UniqueNikNumber, String> {

    @Autowired
    private PendudukService service;

    @Override
    public boolean isValid(String nik, ConstraintValidatorContext constraintValidatorContext) {
        return service.isNikNumberValid(nik);
    }
}
