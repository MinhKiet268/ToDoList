package com.example.todolist.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String username;
    private String password;
    private String imageUrl;
    private String email;
    private String Gender;
}
