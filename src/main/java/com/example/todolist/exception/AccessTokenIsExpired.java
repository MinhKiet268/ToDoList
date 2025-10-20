package com.example.todolist.exception;

public class AccessTokenIsExpired extends RuntimeException{
    public AccessTokenIsExpired(String message){super( message);}
}
