package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.ExerciseModel;
import com.example.fitsyncapi.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutRecommendationService {

    private final ExerciseRepository exerciseRepository;

    public WorkoutRecommendationService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Get recommended exercises based on optional difficulty and target calorie burn.
     * If either parameter is null, it will be ignored in the query.
     *
     * @param difficultyLevel - "Easy", "Medium", or "Hard" (nullable)
     * @param targetCalories  - Total calories to burn in 30 minutes (nullable)
     * @return List of matching ExerciseModel
     */
    public List<ExerciseModel> getRecommendations(String difficultyLevel, Double targetCalories) {
        return exerciseRepository.findRecommended(difficultyLevel, targetCalories);
    }
}
