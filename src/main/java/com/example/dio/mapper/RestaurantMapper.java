package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisingType;
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
     * Converts a {@link CuisingType} enum to its string representation.
     *
     * @param cuisingType The {@link CuisingType} enum to be converted.
     * @return A {@link String} representation of the cuisine type.
     */
   default String mapToString (CuisingType cuisingType){
        if(cuisingType==null)
        {
            return null;
        }
        return cuisingType.getCuisine();
    }

    /**
     * Converts a string to the corresponding {@link CuisingType} enum.
     *
     * @param cuisingType The string representation of the cuisine type.
     * @return The corresponding {@link CuisingType}  value.
     * @throws IllegalArgumentException if the provided string does not match any valid cuisine type.
     */
    default CuisingType mapToCuisingType(String cuisingType){
        if(cuisingType==null)
        {
            return null;
        }
        CuisingType cuisingType1=new CuisingType();
        cuisingType1.setCuisine(cuisingType);

        return cuisingType1;
    }
}

