package com.projects.EmployeeManagementSystem.customExpections;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
