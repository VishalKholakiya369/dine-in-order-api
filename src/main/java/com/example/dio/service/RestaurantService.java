package com.example.dio.service;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {


    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);


}
