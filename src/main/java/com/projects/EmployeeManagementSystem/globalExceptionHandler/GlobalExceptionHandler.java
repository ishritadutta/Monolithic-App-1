package com.projects.EmployeeManagementSystem.globalExceptionHandler;

import com.projects.EmployeeManagementSystem.customExpections.DuplicateEmailException;
import com.projects.EmployeeManagementSystem.customExpections.EmployeeIsDeletedOrInactiveException;
import com.projects.EmployeeManagementSystem.customExpections.ResourceNotFoundException;
import com.projects.EmployeeManagementSystem.dto.ErrorResponse;
import com.projects.EmployeeManagementSystem.dto.ErrorValidationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    ErrorResponse er = new ErrorResponse();
    ErrorValidationResponse evr = new ErrorValidationResponse();

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exp, HttpServletRequest req) {
        er.setTimestamp(LocalDateTime.now());
        er.setStatus(HttpStatus.NOT_FOUND.value());
        er.setError(HttpStatus.NOT_FOUND.name());
        er.setMessage("Employee not found, please try with a valid employee id");
        er.setPath(req.getRequestURI());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeIsDeletedOrInactiveException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeIsDeletedOrInactiveException(EmployeeIsDeletedOrInactiveException exp, HttpServletRequest req) {
        er.setTimestamp(LocalDateTime.now());
        er.setStatus(HttpStatus.BAD_REQUEST.value());
        er.setError(HttpStatus.BAD_REQUEST.name());
        er.setMessage("Requested employee is deleted or inactive, please try with a valid employee id");
        er.setPath(req.getRequestURI());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException exp, HttpServletRequest req) {
        er.setTimestamp(LocalDateTime.now());
        er.setStatus(HttpStatus.BAD_REQUEST.value());
        er.setError(HttpStatus.BAD_REQUEST.name());
        er.setMessage("Email Id already exists, Use a valid Email Id");
        er.setPath(req.getRequestURI());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDAOException(DataIntegrityViolationException exp, HttpServletRequest req) {
        er.setTimestamp(LocalDateTime.now());
        er.setStatus(HttpStatus.BAD_REQUEST.value());
        er.setError(HttpStatus.BAD_REQUEST.name());
        er.setMessage("Check DAO, Something is wrong there");
        er.setPath(req.getRequestURI());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleRequestBodyException(HttpMessageNotReadableException exp, HttpServletRequest req) {
        er.setTimestamp(LocalDateTime.now());
        er.setStatus(HttpStatus.BAD_REQUEST.value());
        er.setError(HttpStatus.BAD_REQUEST.name());
        er.setMessage("Please check the request body, the value or field entered seemed to be Invalid");
        er.setPath(req.getRequestURI());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    //FieldError
   @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorValidationResponse> handleMethodArgumentValidationForFieldErrorsException(MethodArgumentNotValidException exp,HttpServletRequest req){
            Map<String,List<String>> erMap = new HashMap<>();
            for(FieldError error : exp.getBindingResult().getFieldErrors()){
              //  erMap.put(error.getField(),error.getDefaultMessage());
                erMap.computeIfAbsent(error.getField(),k -> new ArrayList<>())
                        .add(error.getDefaultMessage());
            }
            evr.setTimestamp(LocalDateTime.now());
            evr.setStatus(HttpStatus.BAD_REQUEST.value());
            evr.setMessage("Validation violated, please send valid data");
            evr.setPath(req.getRequestURI());
            evr.setErrors(erMap);
            return new ResponseEntity<>(evr,HttpStatus.BAD_REQUEST);
        }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorValidationResponse> handleMethodArgumentValidationForObjectErrorsException(MethodArgumentNotValidException exp, HttpServletRequest req) {
//        Map<String, String> erMap = new HashMap<>();
//        exp.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            erMap.put(fieldName, errorMessage);
//        });
//            evr.setTimestamp(LocalDateTime.now());
//            evr.setStatus(HttpStatus.BAD_REQUEST.value());
//            evr.setMessage("Validation violated, please send valid data");
//            evr.setPath(req.getRequestURI());
//            evr.setErrors(erMap);
//
//        return new ResponseEntity<>(evr, HttpStatus.BAD_REQUEST);
//
//    }
}