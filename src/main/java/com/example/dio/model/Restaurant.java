package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private long coNo;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "opens_at")
    private LocalDateTime opens_at;

    @Column(name = "closes_at")
    private LocalDateTime closesAt;

    @Column(name = "diet_type")
    private DietType dietType;

    @ManyToMany(mappedBy = "CusineType",fetch = FetchType.EAGER)
    private List<CuisingType> cuisingType;

}
