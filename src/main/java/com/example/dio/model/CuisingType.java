package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cuisingtype")
public class CuisingType {

    @Id
    private String cuisine;

    @ManyToMany()
    private List<Restaurant> restaurants;
}
