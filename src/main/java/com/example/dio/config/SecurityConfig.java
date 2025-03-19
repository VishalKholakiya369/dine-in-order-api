package com.example.dio.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final AppEnv env;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String baseUrl = env.getBaseUrl();

     return http.csrf(csrf->csrf.disable())
                .securityMatchers(match ->match.requestMatchers(baseUrl + "/**","/login/**","/logout/**"))//basically used to configure filter chain to acept request made to a specific pattern default ="/**"
                .authorizeHttpRequests(authorize->authorize.requestMatchers(
                        baseUrl+"/register",
                        baseUrl+"/restaurants/{restaurantId}/food-items"
                ).permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
