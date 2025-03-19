package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItem mapToFoodItem(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);

    public List<FoodItemResponse> mapToListFoodItemResponse(List<FoodItem> foodItems);


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
    default String mapToString (CuisineType cuisineType){
        if(cuisineType ==null)
        {
            return null;
        }
        return cuisineType.getCuisine();
    }
    default CuisineType mapToCuisineType(String cuisineType){
        if(cuisineType==null)
        {
            return null;
        }
        CuisineType cuisine1 =new CuisineType();
        cuisine1.setCuisine(cuisineType);

        return cuisine1;
    }

    default String mapToString(Image image){
        if(image ==null)
        {
            return null;
        }
        return image.getImageUrl();
    }
    default Image mapToImage(String image){
        if(image==null)
        {
            return null;
        }
        Image image1 =new Image();
        image1.setImageUrl(image);

        return image1;
    }
}
