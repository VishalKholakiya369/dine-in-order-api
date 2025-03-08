package com.example.dio.dto.request;


import com.example.dio.enums.TableStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {


    @Min(value = 1, message = "Table number must be a positive integer")
    private long tableNo;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 20, message = "Capacity cannot be more than 20")
    private long tableCapacity;


    private TableStatus tableStatus;

}
