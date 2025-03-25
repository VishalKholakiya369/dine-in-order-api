package com.example.dio.service.hellper;

import com.example.dio.config.AppEnv;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
@AllArgsConstructor
public class TokenGeneratorServiceHelper {

    private final AppEnv env;
    private final JWTService jwtService;


    public String generateToken(TokenType tokenType, Map<String, Object> claim, Instant shouldExpireAt) {
        TokenPayload tokenPayload = generateTokenPayload(tokenType,claim,shouldExpireAt);

        String token =  jwtService.generateToken(tokenPayload);

       long maxAge =  Duration.between(Instant.now(),shouldExpireAt).getSeconds();

       return generateCookie(tokenType,token,maxAge);
    }


    public TokenPayload generateTokenPayload(TokenType type, Map<String, Object> claim, Instant shouldExpireAt) {
        Instant issueAt = calculateIssueType(type, shouldExpireAt);

        return new TokenPayload(claim, issueAt, shouldExpireAt);
    }

    private Instant calculateIssueType(TokenType type, Instant shouldExpireAt) {
        Instant issueAt;
        switch (type) {
            case ACCESS -> {
                issueAt = shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getAccessValidity());
            }
            case REFRESH -> {
                issueAt = shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getRefreshValidity());
            }
            default -> throw new IllegalArgumentException("Invalid Token Type !");
        }
        return issueAt;
    }

    private String generateCookie(TokenType tokenType,String token,long maxAge){
      return   ResponseCookie.from(tokenType.type(),token)
                .domain(env.getDomain().getName())
                .path("/")
                .sameSite(env.getDomain().getSafe_site())
                .httpOnly(true)
                .secure(env.getDomain().isSecure())
                .maxAge(maxAge)
                .build()
                .toString();



    }


}
