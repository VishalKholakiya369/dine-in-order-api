package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity

@EntityListeners(AuditingEntityListener.class)
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private long restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_no")
    private String coNo;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "opens_at")
    private LocalTime opens_at;

    @Column(name = "closes_at")
    private LocalTime closes_at;

    @Column(name = "diet_type")
    @Enumerated(EnumType.STRING)
    private List<DietType> dietType;

    @CreatedDate
    private LocalDate createAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTable> tables;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;


    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoNo() {
        return coNo;
    }

    public void setCoNo(String coNo) {
        this.coNo = coNo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public LocalTime getOpens_at() {
        return opens_at;
    }

    public void setOpens_at(LocalTime opens_at) {
        this.opens_at = opens_at;
    }

    public LocalTime getCloses_at() {
        return closes_at;
    }

    public void setCloses_at(LocalTime closes_at) {
        this.closes_at = closes_at;
    }

    public List<DietType> getDietType() {
        return dietType;
    }

    public void setDietType(List<DietType> dietType) {
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

    public List<CuisineType> getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(List<CuisineType> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
