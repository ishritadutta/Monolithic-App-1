package com.projects.EmployeeManagementSystem.service;

import com.projects.EmployeeManagementSystem.customExpections.DuplicateEmailException;
import com.projects.EmployeeManagementSystem.customExpections.EmployeeIsDeletedOrInactiveException;
import com.projects.EmployeeManagementSystem.customExpections.ResourceNotFoundException;
import com.projects.EmployeeManagementSystem.dto.EmployeeOnlyAllowedFieldUpdateDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeResponseDTO;
import com.projects.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.projects.EmployeeManagementSystem.model.Employee;
import com.projects.EmployeeManagementSystem.model.EmployeeStatus;
import com.projects.EmployeeManagementSystem.repo.EmployeeRepo;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepo repo;

    EmployeeMapper em = Mappers.getMapper(EmployeeMapper.class);

    LocalDateTime now = LocalDateTime.now();

    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(Pageable pageable, String search){
                Page<Employee> page;
                if(search==null) {
                    //List<Employee> allEmp = repo.findAll(pageable).getContent();
                    //List<Employee> allEmp = repo.findAllByIsDeletedFalse(pageable);
                    page = repo.findAllByIsDeletedFalse(pageable);
                }else{
                    page=repo.findAllByIsDeletedFalseAndFirstNameContainingIgnoreCase(pageable,search);
                }

            List<Employee> allEmp = page.getContent();
            List<EmployeeResponseDTO> mapEmp = em.fetchEmployeeMapper(allEmp);
            logger.info("Fetching user from page nemer: "+pageable.getPageNumber());
            return new ResponseEntity<>(mapEmp, HttpStatus.OK);

    }

    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(int id){
        Employee emp = repo.findById(id).orElseThrow(() ->{
            logger.error("Employee not found with id: "+ id);
                return new ResourceNotFoundException("Employee Id: "+id+" is not found, Please try with a valid Id");
        });
        if(emp.isDeleted()==true) {
            throw new EmployeeIsDeletedOrInactiveException("Employee is deleted");
        }
        EmployeeResponseDTO mapEmp = em.fetchSingleEmployee(emp);

        logger.info("User is found with Id: {}", id);
        return new ResponseEntity<>(mapEmp, HttpStatus.OK);
    }

    public ResponseEntity<?> addNewEmployee(EmployeeRequestDTO requestDTO) {

        //Unique Email Check 1
//        Optional<Employee> emp1 = repo.findByEmail(requestDTO.getEmail());
//        if(emp1.isPresent()){
//            throw new RuntimeException("Email already exists");
//        }

        //Unique Email Check 2
        if(repo.existsByEmail(requestDTO.getEmail())){
            throw new DuplicateEmailException("Email already exists");
        }

        Employee emp = em.createEmployeeMapper(requestDTO);

        emp.setStatus(EmployeeStatus.ACTIVE); /**Already used in Mapper**/
        emp.setDeleted(false); /**Already used in Mapper**/
        emp.setJoiningDate(now);
        //emp.setCreatedAt(now);  //Audit
        //emp.setUpdatedAt(now);  //Audit
        Employee savedEmp = repo.save(emp);
        EmployeeResponseDTO response = em.fetchSingleEmployee(savedEmp);
        logger.info("User is successfully created with Email: {}",requestDTO.getEmail());
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    public ResponseEntity<?> updateEmployee(EmployeeRequestDTO requestDTO, int id) {
        Employee emp = repo.findById(id).orElseThrow(()-> {
            logger.error("Employee not found with id: {}", id);
            return new ResourceNotFoundException("Employee Id: " + id + " is not found, Please try with a valid Id");
        });
        if(emp.isDeleted() || emp.getStatus()==EmployeeStatus.INACTIVE){
            logger.error("Employee is deleted or inactive with id: "+ id);
            throw new EmployeeIsDeletedOrInactiveException("Employee is Deleted");
        }
        em.updateEmployeeMapper(requestDTO,emp);

        /* Email uneditable */
//        if(!requestDTO.getEmail().equals(emp.getEmail()) && requestDTO.getEmail()!=null){
//            throw new RuntimeException("Email cannot be updated");
//        }
        //emp.setUpdatedAt(now);
        Employee savedEmp = repo.save(emp);
        EmployeeResponseDTO response = em.fetchSingleEmployee(savedEmp);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<?> updateOnlyAllowedFields(EmployeeOnlyAllowedFieldUpdateDTO updateDTO, int id){
            Employee emp = repo.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("Employee Id: "+id+" is not found, Please try with a valid Id"));
            em.updateOnlyAllowedFields(updateDTO,emp);
            emp.setUpdatedAt(now);
            Employee savedEmp = repo.save(emp);
            EmployeeResponseDTO response = em.fetchSingleEmployee(savedEmp);
            return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteEmployee(int id) {
        Employee emp = repo.findById(id).orElseThrow(() ->{
            logger.error("Employee not found with id: {}", id);
            return new ResourceNotFoundException("Employee Id: "+id+" is not found, Please try with a valid Id");
        });
        if(emp.isDeleted()){
            throw new EmployeeIsDeletedOrInactiveException("Employee already deleted");
        }
        emp.setDeleted(true);
        emp.setStatus(EmployeeStatus.INACTIVE);
        repo.save(emp);
        logger.warn("Soft deleting employee with id: "+id);
        return new ResponseEntity<>(emp.getFirstName()+" is deleted",HttpStatus.OK);
    }
}
