package com.example.todolist.service;

import com.example.todolist.dtoRequest.UserCreationRequestDTO;
import com.example.todolist.dtoResponse.UserResponse;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.exception.AlreadyExistException;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.mapper.UserMapper;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // This annotation generates a constructor with 1 parameter for each field that requires special handling. All final fields get a parameter, as well as any fields that are marked as @NonNull that aren't initialized where they are declared. For those fields marked with @NonNull, an explicit null check is also generated.
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.printf("Loading user by username: %s%n", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with username: " + username));

        return new User(user.getUsername()
                , passwordEncoder.encode(user.getPassword())
                , user.getAuthorities());
    }


    public UserResponse registerUser(UserCreationRequestDTO userDTO) {
        // Check if username or email already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new AlreadyExistException("Username already exists");
        }
        UserEntity user = userRepository.save(userMapper.toEntity(userDTO));
        // Encode the password before saving
        return userMapper.toResponseDto(user);
    }


}
