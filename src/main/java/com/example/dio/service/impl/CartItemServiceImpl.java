package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final TableRepository tableRepository;
    private final FoodItemRepository foodItemRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;


    @Override
    public CartItemResponse createCartItem(long tableId, long itemId, int quantity) {
        RestaurantTable restaurantTable = tableRepository.findById(tableId)
                .orElseThrow(()-> new InvalidParameterException("table not found"));
        FoodItem foodItem = foodItemRepository.findById(itemId)
                .orElseThrow(()-> new UserNotFoundByIdException("food not found"));

        CartItem cartItem = cartItemRepository.save(getCartItem(quantity,foodItem,restaurantTable));

        cartItem.setFoodItem(foodItem);
        cartItem.setRestaurantTable(restaurantTable);
        return  cartItemMapper.mapTOCartItemResponse(cartItem);
    }

    private static  CartItem getCartItem(int quantity,FoodItem foodItem,RestaurantTable restaurantTable){
        CartItem cartItem = new CartItem();
        cartItem.setOrdered(false);
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(foodItem.getItemPrice()* cartItem.getQuantity());
        cartItem.setRestaurantTable(restaurantTable);

        return  cartItem;
    }
}
