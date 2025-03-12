package com.example.dio.dto.response;

import com.example.dio.enums.ItemAvailability;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodItemResponse {

    private String itemName;

    private String cuisineType;

    private double itemPrice;

    private String itemDescription;

    private long itemStock;

    private ItemAvailability itemAvailability;
}
