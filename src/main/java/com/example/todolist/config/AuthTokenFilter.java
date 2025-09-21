package com.example.todolist.config;


import com.example.todolist.entity.UserEntity;
import com.example.todolist.service.CustomUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null && jwtUtil.validateToken(jwt)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        new UserEntity(jwtUtil.getUsernameFromToken(jwt))
                        , null
                        , jwtUtil.getAuthoritiesFromToken(jwt)); // Create authentication token

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // adding more details to the authentication object, not mandatory but good to have to track and prevent fraud
                SecurityContextHolder.getContext().setAuthentication(authentication); // setting the authentication in the context, so that later spring security can use it to authorize requests this only lasts for a single request
                // SecurityContext will hold the userdetail and can be access by entire app and authorize the user role
                // SecurityContext only hold one userdetails, if you do multiple authenticate it will be overwrited
            }
        } catch (Exception e) {
            System.out.println("Cannot set user authentication: " + e);
        }
        filterChain.doFilter(request, response); // continue the filter chain regardless of whether authentication was

    }

    private String parseJwt(HttpServletRequest request) { // this method extracts JWT from the Authorization header
        String HeaderAuth = request.getHeader("Authorization");
        if(HeaderAuth != null && HeaderAuth.startsWith("Bearer ")){
            return HeaderAuth.substring(7); // Extract token after "Bearer "
        }
        return null;
    }
}
