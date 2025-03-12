package com.example.dio.repository;

import com.example.dio.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    @Query("SELECT f FROM FoodItem f INNER JOIN f.categories c " +
            "WHERE c.category IN :categories " +
            "GROUP BY f.itemId " +
            "HAVING COUNT(DISTINCT c.category) = :categoryCount")
    List<FoodItem> findFoodItemsByCategory(
            @Param("categories") List<String> categories,
            @Param("categoryCount") int categoryCount);
}

