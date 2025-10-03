package com.example.todolist.config;

import com.example.todolist.entity.UserEntity;
import com.example.todolist.service.CustomUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {

    private final CustomUserService customUserService;

    private final AuthEntryPointJwt unauthorizedHandler;

    private final AuthTokenFilter authenticationFilter;


    // you only take what you need but not the entire AuthenticationManager
    // this is to avoid circular dependency
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/auth/**").permitAll() // allow all requests to /api/auth/**
                        .anyRequest().authenticated()                         // other request is not /api/auth/** must be authenticated to access
                )
                .csrf(AbstractHttpConfigurer::disable) // disable Cross-Site Request Forgery
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // enable CORS with custom configuration
                .userDetailsService(customUserService) // you can add this in if you have more than one class that implements UserDetailsService, if you have only one of it then spring will automatic autowire it for the AuthenticationManager for you
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); // add the perRequestFilter
        return http.build();
    }


// CORS configuration can be added here if needed
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public org.springframework.web.cors.CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // allow only this origin to access the API
                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(Arrays.asList("*")); // allow all headers
                config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
                config.setAllowCredentials(true);
                return config;
            };
        };
    }


//    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration auth) throws Exception {
//        auth.getAuthenticationManager().
//
//        return auth.getAuthenticationManager();
//    }
}
