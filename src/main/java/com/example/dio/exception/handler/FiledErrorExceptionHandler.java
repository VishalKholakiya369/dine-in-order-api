package com.example.dio.exception.handler;

import com.example.dio.util.FieldErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.dio.util.FieldErrorResponse.createFiledError;

@RestControllerAdvice
public class FiledErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//        List<FieldErrorResponse.CustomFieldError> errors = new ArrayList<>();
//
//        List<ObjectError> objectErrors = ex.getAllErrors();
//
//        for(ObjectError objectError : objectErrors){
//            FieldError fieldError = (FieldError) objectError;
//            FieldErrorResponse.CustomFieldError error = cerateFieldError(fieldError);
//            errors.add(error);
//
//        }
        List<FieldErrorResponse.CustomFieldError> errors =ex.getAllErrors().stream()
                .map(error -> (FieldError)error)
                .map(this::cerateFieldError)
                .toList();
        FieldErrorResponse error = createFieldErrorResponse(status, errors);





        return ResponseEntity.status(status).body(error);
    }


    private static FieldErrorResponse createFieldErrorResponse(HttpStatusCode status, List<FieldErrorResponse.CustomFieldError> errors) {
        return FieldErrorResponse.builder()
                .type(status.toString())
                .status(status.value())
                .rootCouse("Invalid Input")
                .errors(errors)
                .build();
    }
    private FieldErrorResponse.CustomFieldError cerateFieldError (FieldError fieldError){
       return  FieldErrorResponse.createFiledError(
               fieldError.getDefaultMessage(),
               fieldError.getRejectedValue(),
               fieldError.getField());

    }
}
