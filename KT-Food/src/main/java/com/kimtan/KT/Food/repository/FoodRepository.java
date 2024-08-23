package com.kimtan.KT.Food.repository;

import com.kimtan.KT.Food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name like %:keyword% " +
            "OR f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
