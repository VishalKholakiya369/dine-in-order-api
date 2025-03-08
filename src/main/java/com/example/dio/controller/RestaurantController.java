package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private final RestaurantService restaurantService ;

    @PostMapping("/restaurant_register/{userId}")
    public ResponseEntity<ResponseStructur<RestaurantResponse>> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest,@PathVariable long userId){
        RestaurantResponse restaurantResponse = restaurantService.createRestaurant(restaurantRequest,userId);
        return ResponseBuilder.created("Restaurant Created",restaurantResponse);
    }
}
