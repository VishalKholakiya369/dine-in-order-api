package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;

public interface UserService {


  public UserResponse registerUser(RegistrationRequest registrationRequest);

  public  UserResponse findUserById(long userId);
  public UserResponse updateUserById(long userId, UserRequest userRequest);


}
