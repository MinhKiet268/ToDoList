package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String priority;
    private String status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity owner;

}
