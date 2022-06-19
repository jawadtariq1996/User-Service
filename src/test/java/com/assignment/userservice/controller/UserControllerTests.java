package com.assignment.userservice.controller;


import com.assignment.userservice.dto.UserDto;
import com.assignment.userservice.entities.User;
import com.assignment.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.assignment.userservice.common.UserCreationHelper.populateUser;
import static com.assignment.userservice.common.UserCreationHelper.populateValidUserDto;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @MockBean
    UserService userService;

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUserEndpoint_WithValidRequest() throws Exception {

        UserDto userDto = populateValidUserDto();

        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(populateUser());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.post("/v1/user-service/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void testCreateUserEndpoint_WithEmptyFirstName() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("Bob");
        userDto.setEmail("alexbob@gmail.com");
        userDto.setDateOfBirth(LocalDate.of(2001,12,20));

        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(new User());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.post("/v1/user-service/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testCreateUserEndpoint_WithEmptyLastName() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Alex");
        userDto.setEmail("alexbob@gmail.com");
        userDto.setDateOfBirth(LocalDate.of(2001,12,20));

        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(new User());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.post("/v1/user-service/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testCreateUserEndpoint_WithInvalidDOB() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Alex");
        userDto.setLastName("Bob");
        userDto.setEmail("alexbob@gmail.com");
        userDto.setDateOfBirth(LocalDate.now());

        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(new User());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.post("/v1/user-service/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testUpdateUserEndpoint_WithSuccessResponse() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Alex");
        userDto.setLastName("Bob");
        userDto.setEmail("alexbob@gmail.com");
        userDto.setDateOfBirth(LocalDate.of(2001,12,20));

        Mockito.when(userService.updateUser(Mockito.any(),Mockito.anyInt())).thenReturn(new User());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.put("/v1/user-service/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }


    @Test
    public void testGetUserEndpoint_WithSuccessResponse() throws Exception {

        Mockito.when(userService.getUserById(Mockito.any())).thenReturn(populateUser());
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.get("/v1/user-service/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void testDeleteEndpoint_WithSuccessResponse() throws Exception {

        Mockito.when(userService.deleteUser(Mockito.anyInt())).thenReturn(true);
        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.delete("/v1/user-service/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(resultActions.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }



}
