package com.example.dio.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@SuperBuilder
@AllArgsConstructor
public class SimpleErrorResponse {
    private String type;
    private int status;
    private String rootCouse;


}
