package com.example.todolist.service;

import com.example.todolist.dtoRequest.UserCreationRequestDTO;

import com.example.todolist.entity.RoleEntity;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.mapper.UserMapper;
import com.example.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    UserMapper userMapper;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<UserCreationRequestDTO> findById(long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return Optional.of(userMapper.toDto(user));
    }

    public UserCreationRequestDTO save(UserCreationRequestDTO userCreationRequestDTO) {
        UserEntity userEntity = userMapper.toEntity(userCreationRequestDTO);
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity("USER"));
        userEntity.setRoles(roles);
        userEntity = userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

}
