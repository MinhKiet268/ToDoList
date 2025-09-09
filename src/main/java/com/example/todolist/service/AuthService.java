package com.example.todolist.service;

import com.example.todolist.dto.UserDTO;

import com.example.todolist.entity.UserEntity;
import com.example.todolist.mapper.UserMapper;
import com.example.todolist.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    UserRepository userRepository;
    UserMapper userMapper;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<UserDTO> findById(long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return Optional.of(userMapper.toDto(user));
    }

    public Optional<UserDTO> save(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return Optional.of(userMapper.toDto(userEntity));
    }

}
