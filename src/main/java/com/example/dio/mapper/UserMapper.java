package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserResponse mapToUserResponse(User user);
     void mapToEntity(RegistrationRequest registrationRequest,@MappingTarget User user) ;
     void mapToNewUser(UserRequest userRequest,@MappingTarget User existingUser);
}
