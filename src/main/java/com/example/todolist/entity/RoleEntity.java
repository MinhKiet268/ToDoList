package com.example.todolist.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // mappedBy must matching the name of the Set field of the UserEntity
    private Set<UserEntity> users;


    @Override
    public String getAuthority() {
        return role;
    }


    public RoleEntity(String role) {
        this.role = role;
    }
}
