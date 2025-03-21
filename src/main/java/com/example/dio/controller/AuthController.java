package com.example.dio.controller;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.service.AuthService;
import com.example.dio.service.TokenGenerationServices;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenGenerationServices tokenGenerationServices;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructur<AuthRecord>> login(@RequestBody LoginRequest request) {

        AuthRecord record = authService.login(request);
        HttpHeaders httpHeaders = tokenGenerationServices.grantAccessAndRefreshToken(record);
        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "login !!", record);
    }
}
