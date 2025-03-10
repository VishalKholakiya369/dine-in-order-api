package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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

    private LocalDate createAt;
    private LocalDate lastModifiedAt;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "tables")
    private List<Staff> staffs;


}
