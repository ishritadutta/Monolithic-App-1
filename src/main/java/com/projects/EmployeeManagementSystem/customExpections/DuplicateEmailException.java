package com.projects.EmployeeManagementSystem.customExpections;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
