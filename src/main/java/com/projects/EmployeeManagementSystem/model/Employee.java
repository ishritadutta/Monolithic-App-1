package com.projects.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    //@Column(unique = true)
    //@Column(updatable = false)
    private String email;
    private String phone;
    private String department;
    private int salary;
    private LocalDateTime joiningDate;
    private int age;
    private String city;
    private boolean isDeleted;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private EmployeeGender gender;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;
    @Enumerated(EnumType.STRING)
    private  EmployeeRole role;
}
