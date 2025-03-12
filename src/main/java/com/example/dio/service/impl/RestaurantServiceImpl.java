package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.CustomAccessDeniedException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repository.CuisineRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;
    private final CuisineRepository cuisineRepository;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest, long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("user not found"));
        if(user instanceof Admin admin){
            Restaurant restaurant = restaurantMapper.mapToRestaurant(restaurantRequest);

            List<CuisineType> cuisines = this.createNonExistingCuisines(restaurant.getCuisineTypes());
            restaurant.setCuisineTypes(cuisines);
            restaurant.setAdmin(admin);
              restaurantRepository.save(restaurant);
            return restaurantMapper.mapToRestaurantResponse(restaurant);
        }
        throw new CustomAccessDeniedException("Only admins can create a restaurant.");
    }



    private List<CuisineType> createNonExistingCuisines(List<CuisineType> cuisineTypes){
    return cuisineTypes.stream()
            .map(type -> cuisineRepository.findById(type.getCuisine())
                    .orElseGet(()-> cuisineRepository.save(type)))
            .toList();
    }
}
