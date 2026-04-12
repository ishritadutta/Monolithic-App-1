package com.projects.EmployeeManagementSystem.controller;

import com.projects.EmployeeManagementSystem.dto.EmployeeOnlyAllowedFieldUpdateDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeResponseDTO;
import com.projects.EmployeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
 //           @RequestParam(required = false,defaultValue = "1") int pageNo,
            @RequestParam int pageNo,           //pageNo. starts from 0
            @RequestParam(required = false,defaultValue = "7") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "ASC") String sortDir,
            @RequestParam(required = false) String search){

        Sort sort =null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        return service.getAllEmployees(PageRequest.of(pageNo, pageSize, sort), search);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeesById(@PathVariable int id){
        return service.getEmployeeById(id);
    }

    @PostMapping("add")
    public ResponseEntity<?> addNewEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO){
        return service.addNewEmployee(requestDTO);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO, @PathVariable int id){
        return service.updateEmployee(requestDTO,id);
    }

    @PutMapping("updateOnlyAllowedFields/{id}")
    public ResponseEntity<?> updateOnlyAllowedDetails(@RequestBody EmployeeOnlyAllowedFieldUpdateDTO updateDTO, @PathVariable int id){
        return service.updateOnlyAllowedFields(updateDTO,id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployee (@PathVariable int id){
        return service.deleteEmployee(id);
    }

}
