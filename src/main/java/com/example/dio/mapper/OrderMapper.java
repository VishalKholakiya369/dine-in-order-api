package com.example.dio.mapper;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;

import com.example.dio.model.Image;
import com.example.dio.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse mapToOrderResponse(Order order);

    default String mapToString (Category category){
        if(category ==null)
        {
            return null;
        }
        return category.getCategory();
    }

    default String mapToString (CuisineType cuisineType){
        if(cuisineType ==null)
        {
            return null;
        }
        return cuisineType.getCuisine();
    }

    default String mapToString(Image image){
        if(image ==null)
        {
            return null;
        }
        return image.getImageUrl();
    }
}
