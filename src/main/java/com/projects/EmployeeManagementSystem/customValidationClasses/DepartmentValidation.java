package com.projects.EmployeeManagementSystem.customValidationClasses;

import com.projects.EmployeeManagementSystem.customValidationAnnotations.ValidDepartment;
import com.projects.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class DepartmentValidation implements ConstraintValidator<ValidDepartment, EmployeeRequestDTO> {

    @Override
    public boolean isValid(EmployeeRequestDTO requestDTO, ConstraintValidatorContext context) {
        if(requestDTO.getDepartment()==null || requestDTO.getDepartment().isEmpty() || requestDTO.getStatus() == null)
            return true;
        switch(requestDTO.getStatus()){
            case ACTIVE:
                List<String> dept = List.of("SALES","HR", "MEDICINE","BLO", "IT","FINANCE");
                if(!(dept.contains(requestDTO.getDepartment()))){
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Invalid department entered, please check")
                            .addPropertyNode("department")
                            .addConstraintViolation();
                    return false;
                }
                break;
            case INACTIVE:
                if(!(requestDTO.getDepartment()==null)){
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Invalid department entered, please check")
                            .addPropertyNode("department")
                            .addConstraintViolation();
                    return false;
                }
        }

        return true;
    }
}
