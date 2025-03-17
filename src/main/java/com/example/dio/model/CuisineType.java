package com.example.dio.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity

@Table(name = "cuisine_type")
public class CuisineType {

    @Id
    private String cuisine;

    @ManyToMany(mappedBy = "cuisineTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
