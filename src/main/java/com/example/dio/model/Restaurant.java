package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
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
    private List<DietType> dietType;

    @ManyToMany(mappedBy = "restaurants",fetch = FetchType.EAGER)
    private List<CuisingType> cuisine;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

}
