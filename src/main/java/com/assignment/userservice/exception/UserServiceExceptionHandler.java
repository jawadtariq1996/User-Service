package com.assignment.userservice.exception;

import com.assignment.userservice.dto.common.ErrorResponse;
import com.assignment.userservice.dto.common.ValidationErrorDto;
import com.assignment.userservice.dto.common.ValidationErrors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserServiceExceptionHandler {

    public static final ErrorResponse EMAIL_NOT_UNIQUE = new ErrorResponse("Email Address already Exist. Please Provide a Unique Email.");
    public static final ErrorResponse USER_DOES_NOT_FOUND = new ErrorResponse("User Does Not Exist in the Database");


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> noSuchElementExceptionHandler(){

      return new ResponseEntity<>(USER_DOES_NOT_FOUND,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> integrityConstraintExceptionHandler(){
        return new ResponseEntity<>(EMAIL_NOT_UNIQUE,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> emptyResultDataAccessException(){
        return new ResponseEntity<>("User Does not Exist in the Database. Please Provide a Valid Id",HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> dateTimeParseException(){
        return new ResponseEntity<>("Please Provide Date in a Valid format (dd-mm-yyyy)",HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDto handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setValidationErrorsList(methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationErrors(fieldError.getField(),fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));

        return validationErrorDto;
    }
}
