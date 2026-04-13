package com.projects.EmployeeManagementSystem.dto;

import com.projects.EmployeeManagementSystem.customValidationAnnotations.*;
import com.projects.EmployeeManagementSystem.model.EmployeeGender;
import com.projects.EmployeeManagementSystem.model.EmployeeRole;
import com.projects.EmployeeManagementSystem.model.EmployeeStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidDepartment
@ValidSalary
public class EmployeeRequestDTO {

    @NotBlank(message = "This field should not be blank")
    @NotNull(message = "This field should not be null")
    private String firstName;
    @NotBlank(message = "This field should not be blank")
    @NotNull(message = "This field should not be null")
    private String lastName;
    @Email(message="Email should be in correct format")
    @ValidEmailDomain(domain = "gmail.com", message="Valid email domain should be entered")
    private String email;
    @Pattern(regexp = "^[6-9][0-9]{9}$",message = "Must be a valid 10-digit mobile number")
    @ValidPhoneNumber(message = "Valid phone number should be entered")
    private String phone;
    private String department;
    @Positive(message = "Must be a positive value")
    private Integer salary;
    private LocalDateTime joiningDate;
    @Min(value =18,message = "Age must be equal or greater than 18")
    @Max(value=60, message ="Age must be equal or less than 60")
    private Integer age;
    private String city;
    private Boolean isDeleted;
    private EmployeeGender gender;
    private EmployeeStatus status;
    @ValidRole(enumClass = EmployeeRole.class, message = "Valid role should be entered")
    private EmployeeRole role;
}
