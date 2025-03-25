package com.example.dio.service;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.service.hellper.TokenGeneratorServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenGenerationServices {

    private final TokenGeneratorServiceHelper tokenGeneratorServiceHelper;

    public HttpHeaders grantAccessToken(AuthRecord authRecord) {
        Map<String,Object> claim = setClaim(authRecord);

        String newAccessToken = tokenGeneratorServiceHelper.generateToken(
                TokenType.ACCESS,
                claim,
                Instant.ofEpochMilli(authRecord.accessExpiration())
        );


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, newAccessToken);

        return headers;
    }


    public HttpHeaders grantAccessAndRefreshToken(AuthRecord authRecord) {

        Map<String, Object> claim = setClaim(authRecord);
        String accessCookie = tokenGeneratorServiceHelper.generateToken(TokenType.ACCESS, claim, Instant.ofEpochMilli(authRecord.accessExpiration()));
        String refreshCookie = tokenGeneratorServiceHelper.generateToken(TokenType.REFRESH, claim, Instant.ofEpochMilli(authRecord.refreshExpiration()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, accessCookie);
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie);
        return headers;

    }

    public Map<String, Object> setClaim(AuthRecord authRecord) {
        return Map.of(
                ClaimName.USER_ID, authRecord.userId(),
                ClaimName.USER_EMAIL, authRecord.email(),
                ClaimName.USER_ROLE, authRecord.role()
        );


    }
}
