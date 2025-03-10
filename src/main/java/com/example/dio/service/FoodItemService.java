package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;

public interface FoodItemService {

    FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId);
}
