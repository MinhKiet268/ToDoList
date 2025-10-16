package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.util.Date;
import java.util.Set;

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


    private Set<Long> tagIds;

    private long listId;

    private String status;
    private Date issueDate;
    private Date dueDate;

    // A many-to-one relationship back to the UserEntity
    // This is the owning side, which has the foreign key.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
