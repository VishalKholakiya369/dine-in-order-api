package com.example.dio.dto.request;

import com.example.dio.dto.rules.Name;
import com.example.dio.enums.ItemAvailability;
import com.example.dio.model.CuisineType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class FoodItemRequest {

    @Name
    private String itemName;

    private String cuisineType;

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

    private List<String> categories;


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemStock(long itemStock) {
        this.itemStock = itemStock;
    }

    public void setItemAvailability(ItemAvailability itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public long getItemStock() {
        return itemStock;
    }

    public ItemAvailability getItemAvailability() {
        return itemAvailability;
    }

    public List<String> getCategories() {
        return categories;
    }
}
