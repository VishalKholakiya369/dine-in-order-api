package com.example.dio.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructur<T> {


    private int status;
    private String message;
    private T data;
}
