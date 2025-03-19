package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse mapTOCartItemResponse(CartItem cartItem);

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
