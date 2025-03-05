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

    /**
     * Map a User entity to a UserResponse DTO.
     *
     * @param user The User Entity to be mapped.
     * @return A UserResponse containing the mapped user details.
     */
    UserResponse mapToUserResponse(User user);

    /**
     * Maps the data from a RegistrationRequest to an existing entity.
     *
     * @param registrationRequest The request object containing user registration details.
     * @param user The target {@link User} entity to be updated with the mapped values.
     */
    void mapToEntity(RegistrationRequest registrationRequest,@MappingTarget User user) ;

    /**
     * Maps the data from {@link UserResponse} to an existing entity.
     *
     * @param userRequest The request object containing user request details.
     * @param existingUser the target {@link User} entity to be updated with the mapped values.
     */
    void mapToNewUser(UserRequest userRequest,@MappingTarget User existingUser);
}