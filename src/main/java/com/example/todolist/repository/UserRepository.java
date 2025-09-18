package com.example.todolist.repository;

import com.example.todolist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//@Repository annotation is not required on interfaces that extend JpaRepository, as Spring Data JPA automatically detects them.
// You use it when you are not using any Spring Data JPA framework and want to indicate that the class is a repository.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

