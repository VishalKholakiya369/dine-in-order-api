package com.example.dio.model;

import com.example.dio.enums.DietType;
import com.example.dio.enums.ItemAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;


}
