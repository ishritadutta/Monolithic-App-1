package com.projects.EmployeeManagementSystem.customValidationAnnotations;

import com.projects.EmployeeManagementSystem.customValidationClasses.EmailDomainValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDomainValidation.class)
public @interface ValidEmailDomain {

    String domain () default "company.com";
    String message() default "Invalid email domain, please check once";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
