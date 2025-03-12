package com.example.dio.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cuisine_type")
public class CuisineType {

    @Id
    private String cuisine;

    @ManyToMany(mappedBy = "cuisineTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;
}
