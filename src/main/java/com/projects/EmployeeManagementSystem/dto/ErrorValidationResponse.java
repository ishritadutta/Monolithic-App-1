package com.projects.EmployeeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidationResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private Map<String, List<String>> errors;
}
