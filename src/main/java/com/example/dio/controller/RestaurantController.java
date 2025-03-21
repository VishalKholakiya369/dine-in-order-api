package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private final RestaurantService restaurantService ;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/restaurant_register")
    public ResponseEntity<ResponseStructur<RestaurantResponse>> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = restaurantService.createRestaurant(restaurantRequest);
        return ResponseBuilder.created("Restaurant Created",restaurantResponse);
    }


}
