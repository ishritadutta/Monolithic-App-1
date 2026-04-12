package com.projects.EmployeeManagementSystem.customValidationClasses;

import com.projects.EmployeeManagementSystem.customValidationAnnotations.ValidEmailDomain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidation implements ConstraintValidator<ValidEmailDomain, String> {

    private String domain;

    @Override
    public void initialize(ValidEmailDomain constraintAnnotation) {

        this.domain="@"+constraintAnnotation.domain();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null && value.endsWith(domain);
        }
    }

