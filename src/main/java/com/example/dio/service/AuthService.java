package com.example.dio.service;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;

public interface AuthService {

    public AuthRecord login(LoginRequest request);
}
