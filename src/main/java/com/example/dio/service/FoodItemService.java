package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;

import java.util.List;

public interface FoodItemService {

    FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId);

    List<FoodItemResponse> findFoodItemsByCategory(List<String> categoryName);
}
