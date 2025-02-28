package com.example.dio.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructur<T> {


    private int status;
    private String message;
    private T data;

    public static <T>ResponseStructur<T> create(HttpStatus status,String message,T data){
        ResponseStructur<T> response = new ResponseStructur<>();
        response.status = status.value();
        response.message = message;
        response.data = data;
        return  response;
    }
}
