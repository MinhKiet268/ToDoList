package com.example.todolist.controller;


import com.example.todolist.config.CookieUtil;
import com.example.todolist.config.JwtUtil;
import com.example.todolist.dtoRequest.UserCreationRequestDTO;
import com.example.todolist.dtoRequest.UserLoginDTO;
import com.example.todolist.dtoResponse.UserResponse;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.service.CustomUserService;
import com.example.todolist.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${cookie.refreshtoken.name}")
    private String refreshTokenName;

    @Value("${cookie.accesstoken.name}")
    private String accessTokenName;

    @Value("${cookie.refreshtoken.path}")
    private String accessTokenPath;

    @Value("${cookie.accesstoken.path}")
    private String refreshTokenPath;

    private final CustomUserService customUserService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signin")
    public ResponseEntity<UserResponse>  authenticateUser(@Valid @RequestBody UserLoginDTO user, String deviceId, HttpServletResponse response) {
        // You only need to put in the username and raw password, the authentication manager will call the loadUserByUsername method and also using the password encoder to match the password
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())); // this will authenticate the user, if not matching then will throw AuthenticationException
        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtUtil.generateToken(userDetails);


        UUID refreshToken = refreshTokenService.createRefreshToken(UUID.randomUUID(),userDetails.getId(), null); // null for deviceId for now

        cookieUtil.responseAccessTokenCookie(response,accessToken);
        cookieUtil.responseRefreshTokenCookie(response,refreshToken.toString());
        return ResponseEntity.status(HttpStatus.OK).body(customUserService.toUserResponseDto(userDetails));
    }


    @PostMapping("/signout")
    public ResponseEntity<String>  authenticateUser(HttpServletResponse response, HttpServletRequest request) {
        refreshTokenService.deleteByRefreshToken(
                cookieUtil.getCookieFromRequest(request,refreshTokenName));
        cookieUtil.deletCookie(response, refreshTokenName, refreshTokenPath);
        cookieUtil.deletCookie(response, accessTokenName, accessTokenPath);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful");
    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserCreationRequestDTO user) {
        UserResponse response = customUserService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response) {

        UUID refreshToken = UUID.fromString(cookieUtil.getCookieFromRequest(request,refreshTokenName));

        System.out.println(refreshToken);

        if(refreshTokenService.isExpired(refreshToken))
        {
            cookieUtil.deletCookie(response, refreshTokenName, refreshTokenPath);
            cookieUtil.deletCookie(response, accessTokenName, accessTokenPath);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Refresh Token Expired. Please login again.");
        }

        UserEntity user = refreshTokenService.findUserByRefreshToken(refreshToken);

        cookieUtil.responseAccessTokenCookie(response, jwtUtil.generateToken(user));

        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");

    }




}
