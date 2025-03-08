package com.example.dio.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cuisingType")
public class CuisingType {

    @Id
    private String cuisine;

    @ManyToMany(mappedBy = "cuisingTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;
}
