package com.assignment.userservice.service;

import com.assignment.userservice.common.UserCreationHelper;
import com.assignment.userservice.dto.UserDto;
import com.assignment.userservice.entities.User;
import com.assignment.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void initDependencies(){
        userService = new UserService(userRepository,new ObjectMapper());
    }

    @Test
    public void testGetUserById_WithSuccess(){

        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(UserCreationHelper.populateUser()));
        User user =  userService.getUserById(1);

        Assertions.assertEquals(user.getId(),1);
        Assertions.assertEquals(user.getFirstName(),"Alex");
        Assertions.assertEquals(user.getLastName(),"Bob");
        Assertions.assertEquals(user.getEmail(),"alexbob@gmail.com");

    }

    @Test
    public void testSaveUser_WithSuccess(){

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(Optional.of(UserCreationHelper.populateUser()));
        User user =  userService.saveUser(UserCreationHelper.populateValidUserDto());

        Assertions.assertEquals(user.getFirstName(),"Alex");
        Assertions.assertEquals(user.getLastName(),"Bob");
        Assertions.assertEquals(user.getEmail(),"alexbob@gmail.com");

    }


    @Test
    public void testUpdateUser_WithSuccess(){

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(Optional.of(UserCreationHelper.populateUser()));
        User user =  userService.saveUser(UserCreationHelper.populateValidUserDto());

        Assertions.assertEquals(user.getFirstName(),"Alex");
        Assertions.assertEquals(user.getLastName(),"Bob");
        Assertions.assertEquals(user.getEmail(),"alexbob@gmail.com");

    }

}
