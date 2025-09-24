package com.example.todolist.dtoResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    private String username;

    private String password;

    private String fullName;

    private String imageUrl;

    private String email;

    private String gender;



}
