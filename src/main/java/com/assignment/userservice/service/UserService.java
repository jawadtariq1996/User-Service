package com.assignment.userservice.service;

import com.assignment.userservice.dto.UserDto;
import com.assignment.userservice.entities.User;
import com.assignment.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository,ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User getUserById(Integer id){

        return userRepository.findById(id).get();
    }

    public User saveUser(UserDto userDto){

        return userRepository.save(objectMapper.convertValue(userDto,User.class));
    }

    public User updateUser(UserDto userDto,Integer id){

        User user = objectMapper.convertValue(userDto,User.class);
        user.setId(id);
        return userRepository.save(user);
    }
    public boolean deleteUser(Integer id){
        userRepository.deleteById(id);
        return true;
    }
}
