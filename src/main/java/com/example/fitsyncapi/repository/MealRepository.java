package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.MealModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealModel, Integer> {


    List<MealModel> findByFoodName(String foodName);
}
