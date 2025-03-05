package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {

  /**
   * Registers a new user based on the provided registration details.
   *
   * @param registrationRequest The request object containing user registration details.
   * @return A containing the register user's information.
   */
  public UserResponse registerUser(RegistrationRequest registrationRequest);

  /**
   *  Fined a user based on the userId.
   *
   * @param userId The containing user id.
   * @return A containing the user's information.
   */
  public  UserResponse findUserById(long userId);

  /**
   * Update a user based on the user id
   *
   * @param userId The containing user id.
   * @param userRequest The containing user's previous data.
   * @return UserResponse the dto that contains user updated information.
   */
  public UserResponse updateUserById(long userId, UserRequest userRequest);


}
