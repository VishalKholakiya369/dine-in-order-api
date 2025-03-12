package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @NotEmpty(message = "Restaurant name can not be null or blank")
    @NotBlank(message = "estaurant name can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9&'-. ]{2,50}$")
    private String name;

    @NotEmpty(message = "Address can not be null or blank")
    @NotBlank(message = "Address can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9.,#\\-/ ]{5,100}$")
    private String address;

    @NotEmpty(message = "contact number can not be null or blank")
    @NotBlank(message = "contact number can not be blank")
    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid contact number")
    private String coNo;

    @NotEmpty(message = "contactEmail can not be null or blank")
    @NotBlank(message = "contactEmail can not be blank")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String contactEmail;


    private LocalTime opens_at;
    private LocalTime closes_at;

    private List<DietType> dietType;
    private List<String> cuisingTypes;
}
