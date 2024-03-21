package com.villatter.validation;

import com.villatter.dto.account.ChangePasswordDTO;
import com.villatter.service.abstraction.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckAuthenticationValidator implements ConstraintValidator<CheckAuthentication, ChangePasswordDTO> {

    @Autowired
    private AccountService service;

    private String label;

    @Override
    public void initialize(CheckAuthentication constraintAnnotation) {
        this.label = constraintAnnotation.label();
    }

    @Override
    public boolean isValid(ChangePasswordDTO value, ConstraintValidatorContext constraintValidatorContext) {
        return service.checkUsernamePassword(value.getUsername(), value.getOldPassword());
    }
}
