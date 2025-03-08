package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import javax.naming.Name;
import java.util.List;

@Entity
@Table(name="admin")
public class Admin extends User{

    @OneToMany(mappedBy = "admin")
    private List<Restaurant> restaurantList;
}
