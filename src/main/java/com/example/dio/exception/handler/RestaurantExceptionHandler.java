package com.example.dio.exception.handler;


import com.example.dio.exception.CustomAccessDeniedException;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> handlerCustomAccessDeniedException(CustomAccessDeniedException ex){
        return ResponseBuilder.notFound(ex.getMessage());
    }
}
