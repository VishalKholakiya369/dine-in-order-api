package com.example.dio.dto.response;

import com.example.dio.enums.IsOrdered;
import lombok.Getter;
import lombok.Setter;



public class CartItemResponse {

    private int quantity;

    private double totalPrice;

    private FoodItemResponse foodItem;

    private boolean isOrdered;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public FoodItemResponse getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItemResponse foodItem) {
        this.foodItem = foodItem;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
