package com.example.dio.dto.response;


import com.example.dio.enums.DietType;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisingType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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


    private List<String> cuisingTypes;


}
