package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "lists")
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String color;

    private boolean isDeleted = false;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
