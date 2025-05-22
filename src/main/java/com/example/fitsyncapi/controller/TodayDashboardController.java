package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.dto.TodayDashboardDTO;
import com.example.fitsyncapi.model.*;
import com.example.fitsyncapi.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/today")
@Tag(name = "Today Dashboard", description = "Daily summary of meals, workouts, and goals")
public class TodayDashboardController {

    private final DailySummaryRepository dailySummaryRepository;
    private final GoalRepository goalRepository;
    private final UserMealRepository userMealRepository;
    private final UserWorkoutRepository userWorkoutRepository;
    private final MealRepository mealRepository;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public TodayDashboardController(
            DailySummaryRepository dailySummaryRepository,
            GoalRepository goalRepository,
            UserMealRepository userMealRepository,
            UserWorkoutRepository userWorkoutRepository,
            MealRepository mealRepository,
            WorkoutRepository workoutRepository
    ) {
        this.dailySummaryRepository = dailySummaryRepository;
        this.goalRepository = goalRepository;
        this.userMealRepository = userMealRepository;
        this.userWorkoutRepository = userWorkoutRepository;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get today's dashboard summary for a user")
    public ResponseEntity<TodayDashboardDTO> getTodayDashboard(@PathVariable int userId) {
        LocalDate today = LocalDate.now();
        TodayDashboardDTO dto = new TodayDashboardDTO();
        dto.setDate(today.toString());

        // Fetch calories in/out from summary
        Optional<DailySummaryModel> summaryOpt = dailySummaryRepository.findByUserIdAndDate(userId, today);
        double caloriesIn = summaryOpt.map(DailySummaryModel::getCaloriesConsumed).orElse(0.0);
        double caloriesOut = summaryOpt.map(DailySummaryModel::getCaloriesBurned).orElse(0.0);
        dto.setCaloriesIn(caloriesIn);
        dto.setCaloriesOut(caloriesOut);
        dto.setNetCalories(caloriesIn - caloriesOut);

        // Fetch goals
        Optional<GoalModel> goalOpt = goalRepository.findById(userId);
        if (goalOpt.isPresent()) {
            GoalModel goal = goalOpt.get();
            double inGoal = goal.getCaloriesInGoal();
            double outGoal = goal.getCaloriesBurnGoal();
            dto.setIntakeGoal(inGoal);
            dto.setBurnGoal(outGoal);

            dto.setIntakeStatus(caloriesIn >= inGoal
                    ? "You reached your intake goal!"
                    : (inGoal - caloriesIn) + " cal left to reach intake goal.");

            dto.setBurnStatus(caloriesOut >= outGoal
                    ? "You hit your burn goal!"
                    : (outGoal - caloriesOut) + " cal left to burn.");
        } else {
            dto.setIntakeGoal(0);
            dto.setBurnGoal(0);
            dto.setIntakeStatus("No intake goal set.");
            dto.setBurnStatus("No burn goal set.");
        }

        // Meals list
        List<String> meals = userMealRepository.findByUserIdAndMealDate(userId, today).stream()
                .map(um -> {
                    MealModel meal = um.getMeal();
                    return String.format("%s (%s) - %.0f cal",
                            meal.getFoodName(), meal.getMealType(), meal.getCalories());
                }).collect(Collectors.toList());
        dto.setMeals(meals);

        // Workouts list
        List<String> workouts = userWorkoutRepository.findByUserIdAndCompletionDate(userId, today).stream()
                .map(uw -> uw.getWorkout().getName())
                .collect(Collectors.toList());
        dto.setWorkouts(workouts);

        return ResponseEntity.ok(dto);
    }
}
