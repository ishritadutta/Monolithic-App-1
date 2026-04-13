package com.projects.EmployeeManagementSystem.customValidationClasses;

import com.projects.EmployeeManagementSystem.customValidationAnnotations.ValidRole;
import com.projects.EmployeeManagementSystem.model.EmployeeRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleEnumValidation implements ConstraintValidator<ValidRole, EmployeeRole> {

    private Set<String> allowedRole;

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        allowedRole=Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());

    }

    @Override
    public boolean isValid(EmployeeRole value, ConstraintValidatorContext constraintValidatorContext) {
        if(value==null) return true;

        return allowedRole.contains(value.name());
    }
}
