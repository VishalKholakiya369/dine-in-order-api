package com.example.dio.model;

import com.example.dio.enums.DietType;
import com.example.dio.enums.ItemAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_cuisine")
    private String itemCuisine;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_stock")
    private long itemStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_availability")
    private ItemAvailability itemAvailability;

    private DietType dietType;
    private LocalDate createAt;
    private LocalDate lastModifiedAt;

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "foodItems")
    private List<Category> categories;



}
