package com.example.todolist.controller;

import com.example.todolist.dtoResponse.ErrorDetail;
import com.example.todolist.dtoResponse.ErrorResponse;
import com.example.todolist.dtoResponse.ValidationErrorResponse;
import com.example.todolist.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInValidException(
            InvalidInputException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public  ResponseEntity<ErrorResponse> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Handle validation errors of the @Valid annotation for the @NotBlank in the DTO
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<ErrorDetail> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();

        // Iterate through all field errors and create a detailed error object for each
        for (FieldError error : result.getFieldErrors()) {
            errors.add(new ErrorDetail(
                    error.getField(),
                    error.getCode(), // Get the validation annotation code (e.g., NotBlank, Email)
                    error.getDefaultMessage()
            ));
        }

        ValidationErrorResponse response = new ValidationErrorResponse("BAD_REQUEST", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CookieNameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCookieTokenNotFoundException(
            CookieNameNotFoundException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessTokenIsExpired.class)
    public ResponseEntity<ErrorResponse> handleAccessTokenIsExpired(AccessTokenIsExpired ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(DataBaseExecutionException.class)
    public ResponseEntity<ErrorResponse> handleDataBaseExecutionException(
            DataBaseExecutionException ex, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
