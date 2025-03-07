package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserResponse {
    private long userId;
    private String username;
    private UserRole role;
    private LocalDateTime creatAt;
    private LocalDateTime lastModifirdAt;
}