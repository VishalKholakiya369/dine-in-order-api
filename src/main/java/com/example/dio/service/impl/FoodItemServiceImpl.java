package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.*;
import com.example.dio.repository.*;
import com.example.dio.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FoodItemServiceImpl implements FoodItemService {

    private final RestaurantRepository restaurantRepository;
    private  final CuisineRepository cuisineRepository;
    private  final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;


    @Transactional
    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest, long restaurantId) {
        // Check if restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UserNotFoundByIdException("Restaurant not found"));



        // Map request to entity
        FoodItem foodItem = foodItemMapper.mapToFoodItem(foodItemRequest);

        System.out.println("in fooditem : " + foodItem.getCuisineType());

        List<Image> images = createNonExistingImage(foodItem.getImages());
        foodItem.setImages(images);

        CuisineType cuisine = createCuisineIfNotExist(foodItem.getCuisineType());

        foodItem.setRestaurant(restaurant);

        List<CuisineType> restaurantCuisines = updateRestaurantCuisineList(restaurant, cuisine);

        // Get categories from request
        List<Category> categories = createNonExistingCategory(foodItem.getCategories());
        log.info("resulted categories: {}", categories.toString());
        foodItem.setCategories(categories);

        // Save food item
        foodItemRepository.save(foodItem);

        // Convert to response DTO
        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }

    @Override
    public List<FoodItemResponse> findFoodItemsByCategory(List<String> categories) {
        if(categories.isEmpty()){
            throw new UserNotFoundByIdException("Food not Found");
        }
        else {
            List<FoodItemResponse> foodItemList = foodItemMapper.mapToListFoodItemResponse(
                    foodItemRepository.findFoodItemsByCategory(
                            categories.stream().distinct().toList(),
                            categories.size()));
            if(foodItemList.isEmpty()){
                throw new UserNotFoundByIdException("Food not Found with this category");
            }
            else{
                return foodItemList;
            }
        }
    }

    @Override
    public List<FoodItemResponse> getFoodItemsByRestaurant(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new UserNotFoundByIdException("Restaurant not found"));

        List<FoodItem> foodItems = foodItemRepository.findAllFoodItemsByRestaurant(restaurant);

        if(foodItems.isEmpty()){
            throw new UserNotFoundByIdException("No food items found for restaurant :"+restaurant.getName());
        }
        return foodItemMapper.mapToListFoodItemResponse(foodItems);
    }

    private List<CuisineType> updateRestaurantCuisineList(Restaurant restaurant, CuisineType cuisine) {
        // Check if cuisine exists in restaurant's list
        List<CuisineType> restaurantCuisines = restaurant.getCuisineTypes();

        if(!restaurantCuisines.contains(cuisine)) {
            restaurantCuisines.add(cuisine);
            restaurant.setCuisineTypes(restaurantCuisines);
            restaurantRepository.save(restaurant);
        }
        return restaurantCuisines;
    }

    private CuisineType createCuisineIfNotExist(CuisineType cuisineType) {
        return cuisineRepository.findById(cuisineType.getCuisine())
                .orElseGet(() -> cuisineRepository.save(cuisineType));
    }

    private List<Category> createNonExistingCategory(List<Category> categories){
        return categories.stream()
                .map(type -> categoryRepository.findById(type.getCategory())
                        .orElseGet(()-> {
                            log.info("Category not found creating one with the name {}", type.getCategory());
                            return categoryRepository.save(type);
                        }))
                .toList();
    }

    private List<Image> createNonExistingImage(List<Image> images){
        return images.stream()
                .map(type -> imageRepository.findById(type.getImageUrl())
                        .orElseGet(()->{
                            return imageRepository.save(type);
                        }))
                .toList();
    }

//    public List<FoodItemResponse> findFoodItemsByCategory(<String categoryName) {
//      //  List<FoodItem> foodItems = foodItemRepository.findFoodItemsByCategory(categoryName);
//        return foodItems.stream()
//                .map(foodItemMapper::mapToFoodItemResponse)
//                .collect(Collectors.toList());
//    }
    }

