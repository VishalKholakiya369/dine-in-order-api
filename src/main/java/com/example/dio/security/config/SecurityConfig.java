package com.example.dio.security.config;

import com.example.dio.config.AppEnv;
import com.example.dio.security.filters.AuthFilter;
import com.example.dio.security.filters.RefreshAuthFilter;
import com.example.dio.security.jwt.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final AppEnv env;
    private final JWTService jwtService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManger(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String baseUrl = env.getBaseUrl();

        return http.csrf(csrf -> csrf.disable())
                .securityMatchers(match ->
                        match.requestMatchers(baseUrl + "/**"))//basically used to configure filter chain to accept request made to a specific pattern default ="/**"
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(getPublicEndpoints())
                        .permitAll()
                        // .requestMatchers(baseUrl+"/restaurant_register/{userId}").hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .formLogin(Customizer.withDefaults())
                .addFilterBefore(new AuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain refreshSecurityFilterChain(HttpSecurity http) throws Exception {
        String baseUrl = env.getBaseUrl();

        return http.csrf(csrf -> csrf.disable())
                .securityMatchers(match ->
                        match.requestMatchers(baseUrl + "/refresh-login/**"))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new RefreshAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private String[] getPublicEndpoints() {
        int size = env.getSecurity().getPublicEndpoints().size();
        String[] endpoints = new String[size];
        for (int i = 0; i < size; i++) {
            endpoints[i] = env.getBaseUrl() + env.getSecurity().getPublicEndpoints().get(i);
        }
        return endpoints;
    }

}
