package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenNotFound extends RuntimeException {
    private final String message;
}
