package com.assignment.userservice.common;

import com.assignment.userservice.dto.UserDto;
import com.assignment.userservice.entities.User;

import java.time.LocalDate;

public class UserCreationHelper {

    public static UserDto populateValidUserDto(){

        UserDto userDto = new UserDto();

        userDto.setFirstName("Alex");
        userDto.setLastName("Bob");
        userDto.setEmail("alexbob@gmail.com");
        userDto.setDateOfBirth(LocalDate.of(2001,12,20));

        return userDto;

    }

    public static User populateUser(){

        User user= new User();
        user.setId(1);
        user.setFirstName("Alex");
        user.setLastName("Bob");
        user.setEmail("alexbob@gmail.com");
        user.setDateOfBirth(LocalDate.of(2001,12,20));

        return user;

    }
}
