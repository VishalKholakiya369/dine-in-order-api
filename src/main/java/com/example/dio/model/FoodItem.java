package com.example.dio.model;

import com.example.dio.enums.DietType;
import com.example.dio.enums.ItemAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity

@EntityListeners(AuditingEntityListener.class)
@Table(name="food_item",indexes = {@Index(name = "idr_name", columnList = "item_name")})
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_stock")
    private long itemStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_availability")
    private ItemAvailability itemAvailability;

    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @CreatedDate
    private LocalDate createAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @CreatedBy
    private String createdBy;

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(mappedBy =  "foodItem")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "foodItem",fetch = FetchType.EAGER)
    private List<Image> images;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public long getItemStock() {
        return itemStock;
    }

    public void setItemStock(long itemStock) {
        this.itemStock = itemStock;
    }

    public ItemAvailability getItemAvailability() {
        return itemAvailability;
    }

    public void setItemAvailability(ItemAvailability itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDate lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
