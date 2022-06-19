package com.assignment.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.time.DurationMax;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
public class UserDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @NotNull
    @Past
    private LocalDate dateOfBirth;
    @NotBlank
    @Email
    private String email;

}
