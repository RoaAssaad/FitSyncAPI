package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.ExerciseModel;
import com.example.fitsyncapi.service.WorkoutRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-recommendations")
@Tag(name = "Workout Recommendation Controller", description = "Recommend workouts based on difficulty or calorie goal")
public class WorkoutRecommendationController {

    private final WorkoutRecommendationService recommendationService;

    public WorkoutRecommendationController(WorkoutRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    @Operation(summary = "Get recommended workouts")
    public ResponseEntity<List<ExerciseModel>> getRecommendations(
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) Double calories
    ) {
        List<ExerciseModel> recommendations = recommendationService.getRecommendations(difficulty, calories);
        return ResponseEntity.ok(recommendations);
    }
}
