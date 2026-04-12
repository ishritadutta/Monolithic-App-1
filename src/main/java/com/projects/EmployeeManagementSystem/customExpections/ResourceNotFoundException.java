package com.projects.EmployeeManagementSystem.customExpections;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
