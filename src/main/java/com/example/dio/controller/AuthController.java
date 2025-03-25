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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/refresh-login")
    private ResponseEntity<ResponseStructur<AuthRecord>> refreshLogin(@CookieValue("rt") String refreshToken){

        AuthRecord record = authService.refreshLogin(refreshToken);
        HttpHeaders httpHeaders = tokenGenerationServices.grantAccessToken(record);

        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "New access token generated !!", record);
    }
}
