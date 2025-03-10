package com.example.dio.dto.request;

import com.example.dio.enums.ItemAvailability;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodItemRequest {

    @NotBlank(message = "Item name cannot be empty")
    @Size(min = 2, max = 50, message = "Item name must be between 2 and 50 characters")
    private String itemName;

    private String itemCuisine;

    @Positive(message = "Item price must be greater than 0")
    private double itemPrice;

    @NotBlank(message = "Item description cannot be empty")
    @Pattern(
            regexp = "^[A-Za-z0-9.,'\"!?()\\s-]{5,500}$",
            message = "Item description must contain only letters, numbers, spaces, and common punctuation"
    )
    private String itemDescription;

    @Min(value = 0, message = "Item stock cannot be negative")
    private long itemStock;

    private ItemAvailability itemAvailability;
}
