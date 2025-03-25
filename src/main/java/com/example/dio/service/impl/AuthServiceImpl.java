package com.example.dio.service.impl;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.service.AuthService;
import com.example.dio.service.TokenGenerationServices;
import com.example.dio.service.hellper.TokenGeneratorServiceHelper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenGenerationServices tokenGenerationServices;
    private final JWTService jwtService;
    private final TokenGeneratorServiceHelper tokenGeneratorServiceHelper;

    @Override
    public AuthRecord login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found !"));

            return generateAuthRecord(user);
        } else {
            throw new UsernameNotFoundException("Failed to authenticate !");
        }
    }

    @Override
    public AuthRecord refreshLogin(String refreshToken) {
        Claims claims = jwtService.parseToken(refreshToken);

        long referenceExpiration = claims.getExpiration().toInstant().toEpochMilli();
        String email = claims.get(ClaimName.USER_EMAIL, String.class);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found !"));

        long accessExpiration = Instant.now().plusSeconds(3600).toEpochMilli();

        AuthRecord authRecord = new AuthRecord(
                user.getUserId(),
                user.getUsername(),
                email,
                user.getRole(),
                accessExpiration,
                referenceExpiration
        );


        return authRecord;

    }

    private static AuthRecord generateAuthRecord(User user) {
        Instant now = Instant.now();
        long accessExpiration = now.plusSeconds(3600).toEpochMilli();
        long referenceExpiration = now.plusSeconds(2 * 30 * 24 * 60 * 60L).toEpochMilli();

        AuthRecord authRecord = new AuthRecord(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                accessExpiration,
                referenceExpiration
        );
        return authRecord;
    }
}
