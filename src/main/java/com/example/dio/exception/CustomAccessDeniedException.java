package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomAccessDeniedException extends RuntimeException {
    private final String message;

}
