package com.example.dio.model;


import com.example.dio.enums.IsOrdered;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "cart_item ")
public class CartItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "total_price")
    private double totalPrice;

    private int quantity;

    @Column(name = "is_ordered")
    private boolean isOrdered;

    @ManyToOne
    private RestaurantTable restaurantTable;

    @ManyToOne
    private FoodItem foodItem;

    @ManyToOne
    private Order order;



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
