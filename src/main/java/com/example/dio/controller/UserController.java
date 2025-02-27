package com.example.dio.controller;

import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class UserController {

    private final UserService userService;
@PostMapping("/register")
    public ResponseEntity<ResponseStructur<User>> registerUser(@RequestBody User user){
        user = userService.registerUser(user);

        ResponseStructur<User> structur = new ResponseStructur<>();
        structur.setData(user);
        structur.setStatus(HttpStatus.CREATED.value());
        structur.setMessage("User Created");

        return new ResponseEntity<ResponseStructur<User>>(structur,HttpStatus.CREATED);
    }


}
