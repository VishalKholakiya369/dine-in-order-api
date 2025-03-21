package com.example.dio.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    /**
     * Help creating the success responses with data including the HttpSatus code,message and data itself.
     *
     * @param status the status of the operation performed
     * @param message the message to the user
     * @param data the data involved in the operation
     * @return resposeEntity of type ResponseStructure or type T(the involved in the operation).
     */
    public static <T> ResponseEntity<ResponseStructur<T>> success(HttpStatus status, String message, T data) {
        ResponseStructur<T> structur = ResponseStructur.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(status).body(structur);
    }

    public static <T> ResponseEntity<ResponseStructur<T>> success(HttpStatus status, HttpHeaders headers, String message, T data) {
        ResponseStructur<T> structur = ResponseStructur.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(structur);
    }

    /**
     * Help creating the success responce with data including the messge and data itself.
     *
     * @param message the message to the user
     * @param data involved in the operation
     * @return resposeEntity of type ResponseStructure or type T(the involved in the operation).

     */
    public static <T> ResponseEntity<ResponseStructur<T>> ok(String message, T data) {
        return success(HttpStatus.OK,message,data);
    }

    /**
     *  Help creating the success responce with data including the messge and data itself.
     *
     * @param message the message to the user
     * @param data involved in the operation
     * @return resposeEntity of type ResponseStructure or type T(the involved in the operation).
     */
    public static <T> ResponseEntity<ResponseStructur<T>> created(String message, T data) {
        return success(HttpStatus.CREATED, message, data);
    }

    /**
     *  Help creating the error response with data including the HttpStatus and rootCause(message)
     *
     * @param status the status of the operation performed
     * @param rootCause the message to the user
     * @return resposeEntity of type SimpleErrorResponse
     */
    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String rootCause) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .rootCouse(rootCause)
                .build();

        return ResponseEntity.status(status).body(error);
    }

    /**
     * Help creating the error response with data including the rootCause(message)
     *
     * @param rootCause the message to the user
     * @return resposeEntity of type SimpleErrorResponse
     */
   public static ResponseEntity<SimpleErrorResponse> notFound(String rootCause){
        return error(HttpStatus.NOT_FOUND,rootCause);
   }


}

