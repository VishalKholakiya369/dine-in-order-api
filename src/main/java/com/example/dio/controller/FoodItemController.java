package com.example.dio.controller;


import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.FoodItemService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("foodItem/{restaurantId}")
    public ResponseEntity<ResponseStructur<FoodItemResponse>> createFoodItem(@Valid @RequestBody FoodItemRequest foodItemRequest, @PathVariable long restaurantId){
        FoodItemResponse foodItemResponse = foodItemService.createFoodItem(foodItemRequest,restaurantId);
       return ResponseBuilder.created("FoodItem created",foodItemResponse);
    }

    @GetMapping("foodItem/category")
    public ResponseEntity<ResponseStructur<List<FoodItemResponse>>> findFoodItemsByCategory(
            @RequestParam List<String> categories) {

        List<FoodItemResponse> foodItems = foodItemService.findFoodItemsByCategory(categories);
        return ResponseBuilder.ok("Food items found", foodItems);
    }

    @GetMapping("restaurant/{restaurantId}/food-items")
    public ResponseEntity<ResponseStructur<List<FoodItemResponse>>> getFoodItemsByRestaurant(
            @PathVariable long restaurantId) {

        List<FoodItemResponse> foodItems = foodItemService.getFoodItemsByRestaurant(restaurantId);
        return ResponseBuilder.ok("Food items for restaurant found", foodItems);
    }
}
