package com.example.todolist.dtoRequest;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequestDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Fullname is mandatory")
    private String fullName;


    private String imageUrl;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Gender is mandatory")
    private String gender;


}