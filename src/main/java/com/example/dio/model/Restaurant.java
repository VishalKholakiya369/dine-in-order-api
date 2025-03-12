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
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//@jakarta.persistence.Table(name = "restaurant")
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

    @ManyToMany
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTable> tables;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;


}
