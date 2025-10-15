package com.example.todolist.service;

import com.example.todolist.dtoRequest.UserCreationRequestDTO;
import com.example.todolist.dtoResponse.UserResponse;
import com.example.todolist.entity.RefreshToken;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.exception.AlreadyExistException;
import com.example.todolist.exception.DataBaseExecutionException;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.mapper.UserMapper;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor // This annotation generates a constructor with 1 parameter for each field that requires special handling. All final fields get a parameter, as well as any fields that are marked as @NonNull that aren't initialized where they are declared. For those fields marked with @NonNull, an explicit null check is also generated.
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserEntity loadUserByUsername(String username) {
        //System.out.printf("Loading user by username: %s%n", username);
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with username: " + username));
    }

    public UserEntity loadUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserResponse registerUser(UserCreationRequestDTO userDTO) {
        // Check if username or email already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new AlreadyExistException("Username already exists");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity user = save(userMapper.toEntity(userDTO));
        // Encode the password before saving
        return userMapper.toResponseDto(user);
    }

    public boolean userTokenExist(UserEntity user,String token) {
        for (RefreshToken refreshToken : user.getRefreshToken()) {
            if(refreshToken.getRefreshToken().toString().equals(token)) {
                return true;
            }
        }
        return (user.getRefreshToken() != null);
    }

    public boolean deleteUser(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setEnable(false);
        save(user);
        return true;
    }

    public UserEntity updateUser(UserEntity user) {
        return update(user);
    }


    public boolean isLogin(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return (user.getRefreshToken() != null);
    }

    public UserResponse toUserResponseDto(UserEntity user) {
        return userMapper.toResponseDto(user);
    }


    public UserEntity save (UserEntity user){
        try {
            return userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Failed to save user to the database." + ex.getMessage());
        }
    }

    public UserEntity update (UserEntity user){
        try {
            return userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Failed to save user to the database." + ex.getMessage());
        }
    }

    public boolean delete (UserEntity user){
        try {
            userRepository.delete(user);
            return true;
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Failed to delete user from the database." + ex.getMessage());
        }
    }

    public UserEntity getUserIdFromContext() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
