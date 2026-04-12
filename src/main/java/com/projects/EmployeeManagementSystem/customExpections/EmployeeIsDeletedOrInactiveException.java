package com.projects.EmployeeManagementSystem.customExpections;

public class EmployeeIsDeletedOrInactiveException extends BusinessException{
    public EmployeeIsDeletedOrInactiveException(String message) {
        super(message);
    }
}
