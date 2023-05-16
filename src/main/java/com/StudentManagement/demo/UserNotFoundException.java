package com.StudentManagement.demo;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}