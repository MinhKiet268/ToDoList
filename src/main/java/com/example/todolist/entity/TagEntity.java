package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String color;

    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable( name = "task_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
