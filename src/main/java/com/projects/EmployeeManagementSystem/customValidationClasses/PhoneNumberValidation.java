package com.projects.EmployeeManagementSystem.customValidationClasses;


import com.projects.EmployeeManagementSystem.customValidationAnnotations.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation implements ConstraintValidator<ValidPhoneNumber,String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.isEmpty()){return true;}
        return value.matches("^(\\+91)?[6-9]\\d{9}$");
    }
}
