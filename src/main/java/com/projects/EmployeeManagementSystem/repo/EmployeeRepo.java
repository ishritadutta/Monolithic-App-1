package com.projects.EmployeeManagementSystem.repo;

import com.projects.EmployeeManagementSystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
   Optional<Employee> findByEmail(String email);

   boolean existsByEmail(String email);

//    List<Employee> findAllByIsDeletedFalse();
        Page<Employee> findAllByIsDeletedFalse(Pageable pageable);

    Page<Employee> findAllByIsDeletedFalseAndFirstNameContainingIgnoreCase(Pageable pageable, String search);

}
