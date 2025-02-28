package com.example.dio.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<ResponseStructur<T>> success(HttpStatus status, String message, T data) {
        ResponseStructur<T> structur = ResponseStructur.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(status).body(structur);
    }

    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String rootCause) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .rootCouse(rootCause)
                .build();

        return ResponseEntity.status(status).body(error);
    }


}
