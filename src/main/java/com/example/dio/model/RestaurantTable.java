package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private long tableId;

    @Column(name = "table_no")
    private long tableNo;

    @Column(name = "table_capacity")
    private long tableCapacity;

    @Column(name = "table_status")
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @CreatedDate
    private LocalDate createAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "tables")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "restaurantTable")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "restaurantTable")
    private List<Order> orders;

}
