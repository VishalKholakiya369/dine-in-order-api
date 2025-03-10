package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItem mapToFoodItem(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);
}
