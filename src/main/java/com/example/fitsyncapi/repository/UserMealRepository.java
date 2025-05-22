package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.UserMealModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserMealRepository extends JpaRepository<UserMealModel, Integer> {

    List<UserMealModel> findByUserIdAndMealDate(int userId, LocalDate mealDate);

    void deleteById(int id);

    @Query("SELECT SUM(um.meal.calories) FROM UserMealModel um WHERE um.user.id = :userId AND um.mealDate = :mealDate")
    Double sumCaloriesByUserIdAndMealDate(int userId, LocalDate mealDate);
}
