package com.projects.EmployeeManagementSystem.customValidationClasses;

import com.projects.EmployeeManagementSystem.customValidationAnnotations.ValidSalary;
import com.projects.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalaryValidation implements ConstraintValidator<ValidSalary, EmployeeRequestDTO> {
    @Override
    public boolean isValid(EmployeeRequestDTO requestDTO, ConstraintValidatorContext context) {
        if (requestDTO.getRole() == null || requestDTO.getSalary() == null) {
            return true;
        }
        double salary = requestDTO.getSalary();

        switch (requestDTO.getRole()) {
            case ADMIN:
            if (salary < 10000 || salary > 25000) {
                addViolation(context, "Salary should be between 10000 and 25000 for ADMIN");
                return false;
            }
            break;

            case EMPLOYEE:
                if (salary < 30000 || salary > 50000) {
                    addViolation(context, "Salary should be between 30000 and 50000 for EMPLOYEE");
                    return false;
                }
                break;

            case HR:
                if (salary < 16000 || salary > 44000) {
                    addViolation(context, "Salary should be between 16000 and 44000 for HR");
                    return false;
                }
                break;

            case MANAGER:
                if (salary < 96000 || salary > 100000) {
                    addViolation(context, "Salary should be between 96000 and 100000 for MANAGER");
                    return false;
                }
                break;
        }

        return true;
    }

    public void addViolation(ConstraintValidatorContext context, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("salary")
                .addConstraintViolation();
    }
}
