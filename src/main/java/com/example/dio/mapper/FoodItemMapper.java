package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItem mapToFoodItem(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);

    default String mapToString (Category category){
        if(category ==null)
        {
            return null;
        }
        return category.getCategory();
    }

    default Category mapToCategory(String category){
        if(category==null)
        {
            return null;
        }
        Category category1 =new Category();
        category1.setCategory(category);

        return category1;
    }
}
