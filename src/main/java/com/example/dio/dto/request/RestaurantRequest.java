package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;


public class RestaurantRequest {

    @NotEmpty(message = "Restaurant name can not be null or blank")
    @NotBlank(message = "estaurant name can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9&'-. ]{2,50}$")
    private String name;

    @NotEmpty(message = "Address can not be null or blank")
    @NotBlank(message = "Address can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9.,#\\-/ ]{5,100}$")
    private String address;

    @NotEmpty(message = "contact number can not be null or blank")
    @NotBlank(message = "contact number can not be blank")
    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid contact number")
    private String coNo;

    @NotEmpty(message = "contactEmail can not be null or blank")
    @NotBlank(message = "contactEmail can not be blank")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String contactEmail;


    private LocalTime opens_at;
    private LocalTime closes_at;

    private List<DietType> dietType;
    private List<String> cuisineTypes;


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

    public List<String> getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(List<String> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }
}
