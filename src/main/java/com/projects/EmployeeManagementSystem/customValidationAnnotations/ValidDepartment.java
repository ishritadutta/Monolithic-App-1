package com.projects.EmployeeManagementSystem.customValidationAnnotations;

import com.projects.EmployeeManagementSystem.customValidationClasses.DepartmentValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentValidation.class)
public @interface ValidDepartment {
    String message() default "Invalid department";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
