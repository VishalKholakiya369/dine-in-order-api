package com.example.dio.repository;

import com.example.dio.model.CuisingType;
import com.example.dio.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuisineRepository extends JpaRepository<CuisingType, String> {

}
