package com.projects.EmployeeManagementSystem.customValidationAnnotations;


import com.projects.EmployeeManagementSystem.customValidationClasses.RoleEnumValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleEnumValidation.class)
public @interface ValidRole {

    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid role, please enter correctly";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
