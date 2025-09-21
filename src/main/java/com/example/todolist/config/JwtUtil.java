package com.example.todolist.config;


import com.example.todolist.entity.RoleEntity;
import com.example.todolist.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret; // Injected from application.properties
    @Value("${jwt.expiration}")
    private int jwtExpirationInMs; // Injected from application.properties
    private SecretKey key; // SecretKey for signing JWTs


    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // Generate the key from the secret
    }

    public String generateToken(UserEntity userDetails) {
        //List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                .setSubject(String.valueOf(userDetails.getId())) // you can use userDetails.getId().toString()
                .addClaims(claims)
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)// parseClaimsJws use this when jwt being signed parseClaimsJws jwt is not signed
                .getBody();
    }

    public Set<RoleEntity> getAuthoritiesFromToken(String token) {
        ArrayList<RoleEntity> roles = (ArrayList<RoleEntity>) getClaimsFromToken(token).get("roles");

        return roles.stream().collect(Collectors.toSet());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token); //
            return true;
        } catch (SecurityException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

}
