package com.example.todolist.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>(); // 0: user, 1: admin


    @OneToMany(cascade = CascadeType.MERGE, fetch =  FetchType.LAZY)
    private Set<TaskEntity> tasks;

    @Column(nullable = false)
    private boolean isEnable = true;


    @Column(nullable = false)
    private boolean isNotLocked = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    // UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Set<RoleEntity> getTasks() {return roles;}

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    public UserEntity (String username) {
        this.username = username;
    }
}
