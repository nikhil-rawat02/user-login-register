package com.StudentManagement.demo;

public class PasswordNotFoundException extends  Exception{
    public PasswordNotFoundException(String message){
        super(message);
    }
}