package com.example.todolist.exception;

public class AlreadyExistException extends  RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
