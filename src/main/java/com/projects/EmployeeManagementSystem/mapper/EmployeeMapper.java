package com.projects.EmployeeManagementSystem.mapper;

import com.projects.EmployeeManagementSystem.dto.EmployeeOnlyAllowedFieldUpdateDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import com.projects.EmployeeManagementSystem.dto.EmployeeResponseDTO;
import com.projects.EmployeeManagementSystem.model.Employee;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    LocalDate local = LocalDateTime.now().toLocalDate();

   //@Mapping(target="status",constant = "ACTIVE")
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="id",ignore = true)
    //@Mapping(target="isDeleted", constant ="false")
    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    Employee createEmployeeMapper(EmployeeRequestDTO requestDTO);

    @Mapping(target = "fullName", expression = "java(employee.getFirstName() + \" \" + employee.getLastName())")
    EmployeeResponseDTO fetchSingleEmployee(Employee employee);


    List<EmployeeResponseDTO> fetchEmployeeMapper(List<Employee> employee);

    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="id",ignore = true)
    @Mapping(target="email",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee updateEmployeeMapper(EmployeeRequestDTO requestDTO, @MappingTarget Employee emp);

   @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee updateOnlyAllowedFields (EmployeeOnlyAllowedFieldUpdateDTO updateDTO, @MappingTarget Employee emp);

}
