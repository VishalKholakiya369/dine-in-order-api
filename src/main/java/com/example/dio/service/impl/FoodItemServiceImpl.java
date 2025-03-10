package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;


    @Transactional
    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId) {
        // Check if restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UserNotFoundByIdException("Restaurant not found"));



        // Check if cuisine exists in restaurant's list
        List<CuisineType> restaurantCuisines = restaurant.getCuisineTypes();

        CuisineType cuisine = restaurantCuisines.stream()
                .filter(c -> c.getCuisine().equalsIgnoreCase(foodItemRequest.getItemCuisine()))
                .findFirst()
                .orElseGet(() -> {
                    if (foodItemRequest.getItemCuisine() == null || foodItemRequest.getItemCuisine().isEmpty()) {
                        throw new IllegalArgumentException("Cuisine name cannot be null or empty");
                    }

                    CuisineType newCuisine = new CuisineType();
                    newCuisine.setCuisine(foodItemRequest.getItemCuisine());

                    return cuisineRepository.save(newCuisine); // Explicit save
                });


        FoodItem foodItem = foodItemMapper.mapToFoodItem(foodItemRequest);
        foodItem.setItemCuisine(cuisine.getCuisine());
        foodItem.setRestaurant(restaurant);

        List<Category> categories = this.createNonExistingCategoey(foodItem.getCategories());
        foodItem.setCategories(categories);
      //  foodItem.setRestaurant(restaurant);

        foodItemRepository.save(foodItem);


        if (!restaurantCuisines.contains(cuisine)) {
            restaurant.getCuisineTypes().add(cuisine);
            restaurantRepository.save(restaurant);
        }

        // Convert to response DTO
        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }
    private List<Category> createNonExistingCategoey(List<Category> categories){
        return categories.stream()
                .map(type -> categoryRepository.findById(type.getCategory())
                        .orElseGet(()-> categoryRepository.save(type)))
                .toList();
    }


    }

