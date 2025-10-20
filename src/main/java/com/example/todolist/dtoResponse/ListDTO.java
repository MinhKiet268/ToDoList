package com.example.todolist.dtoResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ListDTO {
    private Long id;

    @NotBlank
    private String name;
}
