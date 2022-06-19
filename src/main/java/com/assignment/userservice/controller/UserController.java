package com.assignment.userservice.controller;

import com.assignment.userservice.dto.UserDto;
import com.assignment.userservice.entities.User;
import com.assignment.userservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping(value = "/v1/user-service",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto){

          User user =  userService.saveUser(userDto);
          return ResponseEntity.ok(user);
    }
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id ){

        User user =  userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDto userDto,@PathVariable @NotNull Integer id){
        User user =  userService.updateUser(userDto,id);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id ){

        userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }


}
