package com.assignment.userservice.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorDto {

    private List<ValidationErrors> validationErrorsList;

    public ValidationErrorDto(ConstraintViolationException constraintViolationException){
        validationErrorsList = constraintViolationException.getConstraintViolations().stream().
                                                          map(x-> new ValidationErrors(x.getPropertyPath().toString(),x.getMessage()))
                                                          .collect(Collectors.toList());

    }

}
