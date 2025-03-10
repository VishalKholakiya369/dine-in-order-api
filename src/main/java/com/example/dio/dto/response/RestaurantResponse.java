package com.example.dio.dto.response;


import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
public class RestaurantResponse {

    private String name;


    private String address;


    private String coNo;


    private String contactEmail;


    private LocalTime opens_at;


    private LocalTime closes_at;


    private List<DietType> dietType;


    private List<String> cuisineTypes;

    private LocalDate createAt;
    private LocalDate lastModifiedAt;




}
