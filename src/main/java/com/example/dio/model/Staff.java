package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

@Entity
@Table(name="staffs")
public class Staff extends User{

    @CreatedBy
    private String CreateBy;

    @ManyToMany
    private List<RestaurantTable> tables;

}
