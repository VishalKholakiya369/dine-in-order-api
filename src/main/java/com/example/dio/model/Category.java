package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="category")
public class Category {

    @Id
    private String category;

    @ManyToMany
    private List<FoodItem> foodItems;


}
