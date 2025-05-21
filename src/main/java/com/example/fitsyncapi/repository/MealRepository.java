package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.MealModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<MealModel, Integer> {
    Optional<MealModel> findByFoodName(String foodName);
}
