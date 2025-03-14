package com.example.dio.dto.response;

import com.example.dio.enums.IsOrdered;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CartItemResponse {

    private int quantity;

    private double totalPrice;

    private FoodItemResponse foodItem;

    private boolean isOrdered;
}
