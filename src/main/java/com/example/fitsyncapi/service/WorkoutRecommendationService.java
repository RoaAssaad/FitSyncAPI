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


    public List<ExerciseModel> getRecommendations(String difficultyLevel, Double targetCalories) {
        return exerciseRepository.findRecommended(difficultyLevel, targetCalories);
    }
}
