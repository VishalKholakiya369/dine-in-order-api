package com.example.dio.security.filters;

import com.example.dio.exception.InvalidJWTException;
import com.example.dio.exception.TokenNotFound;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.KeyHolder;
import com.example.dio.security.jwt.TokenType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public AuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("Validating request, finding token:{}", TokenType.ACCESS.type());
        //extract token
        Cookie[] cookies = request.getCookies();
        String token = cookies != null ? FilterHelper.extractToken(TokenType.ACCESS, cookies) : null ;
        if (token != null) {
            log.info("Token found with name :{}", TokenType.ACCESS.type());
            Claims claims = jwtService.parseToken(token);
            String email = claims.get(ClaimName.USER_EMAIL, String.class);
            String role = claims.get(ClaimName.USER_ROLE, String.class);

            if ((email != null && !email.isBlank()) && (role != null && !role.isBlank())) {
                log.info("claims : {} & {} extracted successfully", ClaimName.USER_EMAIL, ClaimName.USER_ROLE);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority(role))
                    );

                    authToken.setDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info("Request Authentication Successfully !! ");
                }
            } else {
                log.error("Invalid claims : {} & {} ", ClaimName.USER_EMAIL, ClaimName.USER_ROLE);
            }

        } else {
            log.warn("Token not found with name : {} ", TokenType.ACCESS.type());
        }

        filterChain.doFilter(request, response);
    }
}
