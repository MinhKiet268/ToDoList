package com.example.todolist.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse {
    private String status;
    private List<ErrorDetail> errors;


    public ValidationErrorResponse(String status,List<ErrorDetail> errors) {
        this.errors = errors;
        this.status = status;
    }
}
