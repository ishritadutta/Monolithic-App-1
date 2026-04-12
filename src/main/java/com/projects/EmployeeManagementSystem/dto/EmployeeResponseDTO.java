package com.projects.EmployeeManagementSystem.dto;

import com.projects.EmployeeManagementSystem.model.EmployeeGender;
import com.projects.EmployeeManagementSystem.model.EmployeeRole;
import com.projects.EmployeeManagementSystem.model.EmployeeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String department;
    private Integer salary;
    private Date joiningDate;
    private Integer age;
    private String city;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EmployeeGender gender;
    private EmployeeStatus status;
    private EmployeeRole role;
}
