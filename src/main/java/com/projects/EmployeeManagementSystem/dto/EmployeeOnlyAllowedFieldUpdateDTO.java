package com.projects.EmployeeManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeOnlyAllowedFieldUpdateDTO {

    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;

}
