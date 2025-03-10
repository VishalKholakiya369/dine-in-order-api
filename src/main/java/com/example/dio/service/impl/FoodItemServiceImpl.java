package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.CuisingType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CuisineRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final RestaurantRepository restaurantRepository;
    private  final CuisineRepository cuisineRepository;
    private   FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;


    @Transactional
    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId) {
        // Check if restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UserNotFoundByIdException("Restaurant not found"));

        // Check if cuisine exists in restaurant's list
        List<CuisingType> restaurantCuisines = restaurant.getCuisingTypes();

        CuisingType cuisine = restaurantCuisines.stream()
                .filter(c -> c.getCuisine().equalsIgnoreCase(foodItemRequest.getItemCuisine()))
                .findFirst()
                .orElseGet(() -> {
                    if (foodItemRequest.getItemCuisine() == null || foodItemRequest.getItemCuisine().isEmpty()) {
                        throw new IllegalArgumentException("Cuisine name cannot be null or empty");
                    }

                    CuisingType newCuisine = new CuisingType();
                    newCuisine.setCuisine(foodItemRequest.getItemCuisine());

                    return cuisineRepository.save(newCuisine); // Explicit save
                });


        FoodItem foodItem = foodItemMapper.mapToFoodItem(foodItemRequest);
        foodItem.setItemCuisine(cuisine.getCuisine());  // Assign cuisine string
        foodItem.setRestaurant(restaurant);

        foodItem = foodItemRepository.save(foodItem);

        // Update restaurant with new cuisine (if added)
        if (!restaurantCuisines.contains(cuisine)) {
            restaurant.getCuisingTypes().add(cuisine);
            restaurantRepository.save(restaurant);
        }

        // Convert to response DTO
        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }



    }

