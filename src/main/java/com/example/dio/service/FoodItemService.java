package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;

import java.util.List;

public interface FoodItemService {

   public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId);

    public List<FoodItemResponse> findFoodItemsByCategory(List<String> categoryName);

   public List<FoodItemResponse> getFoodItemsByRestaurant(long restaurantId);
}
