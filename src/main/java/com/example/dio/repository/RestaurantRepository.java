package com.example.dio.repository;

import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findNameByFoodItems_ItemId(long foodId);
}
