package com.example.dio.model;


import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import javax.naming.Name;

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
    private OrderStatus isOrdered;

    @ManyToOne
    private RestaurantTable restaurantTable;

    @ManyToOne
    private FoodItem foodItem;

}
