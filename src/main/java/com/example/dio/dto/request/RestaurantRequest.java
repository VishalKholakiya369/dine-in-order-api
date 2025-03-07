package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisingType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @Pattern(regexp = "^[7-9]d{9}$",message = "Invalid contact number")
    private String coNo;

    @NotEmpty(message = "contactEmail can not be null or blank")
    @NotBlank(message = "contactEmail can not be blank")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String contactEmail;

    @NotEmpty(message = "opens_at can not be null or blank")
    @NotBlank(message = "opens_at can not be blank")
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$",message = "Invalid Time")
    @Column(name = "opens_at")
    private LocalTime opens_at;

    @NotEmpty(message = "closes_at can not be null or blank")
    @NotBlank(message = "closes_at can not be blank")
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$",message = "Invalid Time")
    private LocalTime closes_at;

    private List<DietType> dietType;

    @NotEmpty(message = "Cuisines can not be null or blank")
    @NotBlank(message = "Cuisines can not be blank")
    @Pattern(regexp = "^[A-Za-z ]{3,30}$")
   private List<String> cuisine;
}
