package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    /**
     * Maps a {@link RestaurantRequest} DTO to a {@link Restaurant} entity.
     *
     * @param restaurantRequest The request object containing restaurant details.
     * @return A {@link Restaurant} entity populated with the provided details.
     */
    Restaurant mapToRestaurant(RestaurantRequest restaurantRequest);

    /**
     * Maps a {@link Restaurant} entity to a {@link RestaurantResponse} DTO.
     *
     * @param restaurant The {@link Restaurant} entity to be mapped.
     * @return A {@link RestaurantResponse} containing the restaurant's details.
     */
    RestaurantResponse  mapToRestaurantResponse( Restaurant restaurant);

    /**
     * Converts a {@link CuisineType} enum to its string representation.
     *
     * @param cuisineType The {@link CuisineType} enum to be converted.
     * @return A {@link String} representation of the cuisine type.
     */
   default String mapToString (CuisineType cuisineType){
        if(cuisineType ==null)
        {
            return null;
        }
        return cuisineType.getCuisine();
    }

    /**
     * Converts a string to the corresponding {@link CuisineType} enum.
     *
     * @param cuisingType The string representation of the cuisine type.
     * @return The corresponding {@link CuisineType}  value.
     * @throws IllegalArgumentException if the provided string does not match any valid cuisine type.
     */
    default CuisineType mapToCuisingType(String cuisingType){
        if(cuisingType==null)
        {
            return null;
        }
        CuisineType cuisineType1 =new CuisineType();
        cuisineType1.setCuisine(cuisingType);

        return cuisineType1;
    }
}

