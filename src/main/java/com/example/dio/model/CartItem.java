package com.example.dio.model;


import com.example.dio.enums.IsOrdered;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

}
