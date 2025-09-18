package com.example.todolist.controller;


import com.example.todolist.config.JwtUtil;
import com.example.todolist.dtoRequest.UserCreationRequestDTO;
import com.example.todolist.dtoRequest.UserLoginDTO;
import com.example.todolist.dtoResponse.UserResponse;
import com.example.todolist.service.CustomUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final CustomUserService customUserService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;


    @PostMapping("/signin")
    public String authenticateUser(@Valid @RequestBody UserLoginDTO user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getUsername())); // this will authenticate the user, if not matching then will throw AuthenticationException
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails.getUsername());
    }

//    @PostMapping("/signout")
//    public ResponseBody<UserResponse> login(@Valid @RequestBody UserLoginDTO user) {
//        return null;
//    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserCreationRequestDTO user) {
        UserResponse responseUser = customUserService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
