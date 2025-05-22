package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Integer> {

    // Optional: Find by difficulty only
    List<ExerciseModel> findByDifficultyLevel(String difficultyLevel);

    // Custom query: filter by difficulty and calorie target (30-minute duration)
    @Query("SELECT e FROM ExerciseModel e WHERE " +
            "(:difficulty IS NULL OR e.difficultyLevel = :difficulty) AND " +
            "(:targetCalories IS NULL OR e.caloriesPerMinute * 30 >= :targetCalories)")
    List<ExerciseModel> findRecommended(String difficulty, Double targetCalories);
}
