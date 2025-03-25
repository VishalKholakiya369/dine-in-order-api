package com.example.dio.security.filters;

import com.example.dio.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;


public class FilterHelper {

    public static String extractToken(TokenType tokenType, Cookie[] cookies) {
        String token = null;
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenType.type())) {
                    token = cookie.getValue();
                    break;
                }
            }
        return token;
    }
}
