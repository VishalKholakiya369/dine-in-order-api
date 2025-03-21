package com.example.dio.dto.request;

import com.example.dio.dto.rules.Address;
import com.example.dio.dto.rules.ContactNumber;
import com.example.dio.dto.rules.Name;
import com.example.dio.enums.DietType;
import jakarta.validation.constraints.Email;

import java.time.LocalTime;
import java.util.List;


public class RestaurantRequest {

    @Name
    private String name;

    @Address
    private String address;

    @ContactNumber
    private String coNo;

    @Email
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
