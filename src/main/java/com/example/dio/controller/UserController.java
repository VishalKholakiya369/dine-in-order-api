package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class UserController {

    private final UserService userService;
@PostMapping("/register")
    public ResponseEntity<ResponseStructur<UserResponse>> registerUser(@RequestBody RegistrationRequest registrationRequest){
        UserResponse response = userService.registerUser(registrationRequest);

    return ResponseBuilder.success(HttpStatus.CREATED,"User Created",response);
    }
@GetMapping("/users/{userId}")
  public ResponseEntity<ResponseStructur<UserResponse>> findUserById(@PathVariable long userId){
   UserResponse response = userService.findUserById(userId);
    return ResponseBuilder.success(HttpStatus.OK,"User Found",response);
  }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseStructur<UserResponse>> updateUserById(@PathVariable long userId,@RequestBody UserRequest userRequest){
        UserResponse response = userService.updateUserById(userId,userRequest);
        return ResponseBuilder.success(HttpStatus.OK,"User Updated",response);
    }


}
