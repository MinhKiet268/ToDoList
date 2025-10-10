package com.example.todolist.exception;

public class DataBaseExecutionException extends RuntimeException{
    String exMessage;
    public DataBaseExecutionException(String message){
        super(message);
    }
}
