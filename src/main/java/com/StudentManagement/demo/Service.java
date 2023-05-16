package com.StudentManagement.demo;

import com.StudentManagement.demo.DTO.LoginResposeDto;
import com.StudentManagement.demo.DTO.RegisterRequestDto;

import java.util.HashMap;

@org.springframework.stereotype.Service
public class Service {
    HashMap<String,User> userHashMap = new HashMap<>();

    public User checkLogin(String userEmail, String password) throws UserNotFoundException, PasswordNotFoundException {

        User user = userHashMap.get((userEmail)) ;

        if(user == null){
            throw new UserNotFoundException("User Not found!");
        }
        else if(user.getPassword().equals(password)){
            return user;
        }else {
            throw new PasswordNotFoundException ("password does not match");
        }
    }

    public User registerUser(RegisterRequestDto registerRequestDto) throws Exception {
        User newUser = new User();
        try{
            newUser.setUserName(registerRequestDto.getUserName());
            newUser.setEmailId(registerRequestDto.getUserEmail());
            newUser.setPassword(registerRequestDto.getUserPassword());
            userHashMap.put(registerRequestDto.getUserEmail(),newUser);
            return newUser;
        }catch (Exception e) {
            throw new Exception("Error");
        }
    }
}
