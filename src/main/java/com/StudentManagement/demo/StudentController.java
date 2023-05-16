package com.StudentManagement.demo;

import com.StudentManagement.demo.DTO.LoginRequestDto;
import com.StudentManagement.demo.DTO.LoginResposeDto;
import com.StudentManagement.demo.DTO.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://nikhil-rawat02.github.io/registration/")
@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    Service service;

    @GetMapping("/check")
    public ResponseEntity check() {
        LoginResposeDto loginResposeDto = new LoginResposeDto();
        System.out.println(loginResposeDto);
        return new ResponseEntity<>(loginResposeDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity checkLogin(@RequestParam String userEmail, @RequestParam String userPassword) {
        User user;
        try {
            user = service.checkLogin(userEmail, userPassword);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found Check your email or signIn!", HttpStatus.NOT_FOUND);
        } catch (PasswordNotFoundException e) {
            return new ResponseEntity<>("Invalid Password!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error from database!", HttpStatus.NOT_FOUND);
        }
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        User isRegistered;
        try {
            isRegistered = service.registerUser(registerRequestDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some problem occurred in server try again", HttpStatusCode.valueOf(404));
        }
        System.out.println(isRegistered);
        return new ResponseEntity<>(isRegistered, HttpStatus.CREATED);

    }

}
