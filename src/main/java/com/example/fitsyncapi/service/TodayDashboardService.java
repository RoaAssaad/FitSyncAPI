package com.example.fitsyncapi.service;

import com.example.fitsyncapi.dto.TodayDashboardDTO;
import com.example.fitsyncapi.model.*;
import com.example.fitsyncapi.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodayDashboardService {

    private final DailySummaryRepository dailySummaryRepository;
    private final GoalRepository goalRepository;
    private final UserMealRepository userMealRepository;
    private final UserWorkoutRepository userWorkoutRepository;

    public TodayDashboardService(
            DailySummaryRepository dailySummaryRepository,
            GoalRepository goalRepository,
            UserMealRepository userMealRepository,
            UserWorkoutRepository userWorkoutRepository
    ) {
        this.dailySummaryRepository = dailySummaryRepository;
        this.goalRepository = goalRepository;
        this.userMealRepository = userMealRepository;
        this.userWorkoutRepository = userWorkoutRepository;
    }

    public TodayDashboardDTO getTodayDashboard(int userId) {
        LocalDate today = LocalDate.now();

        double caloriesIn = 0.0;
        double caloriesOut = 0.0;
        double intakeGoal = 0.0;
        double burnGoal = 0.0;
        String intakeStatus = "No intake goal set.";
        String burnStatus = "No burn goal set.";

        // Get today's summary
        Optional<DailySummaryModel> summaryOpt = dailySummaryRepository.findByUserIdAndDate(userId, today);
        if (summaryOpt.isPresent()) {
            DailySummaryModel summary = summaryOpt.get();
            caloriesIn = summary.getCaloriesConsumed();
            caloriesOut = summary.getCaloriesBurned();
        }

        // Get goals
        Optional<GoalModel> goalOpt = goalRepository.findById(userId);
        if (goalOpt.isPresent()) {
            GoalModel goal = goalOpt.get();
            intakeGoal = goal.getCaloriesInGoal();
            burnGoal = goal.getCaloriesBurnGoal();

            if (caloriesIn >= intakeGoal) {
                intakeStatus = "You reached your intake goal!";
            } else {
                intakeStatus = (intakeGoal - caloriesIn) + " cal left to reach intake goal.";
            }

            if (caloriesOut >= burnGoal) {
                burnStatus = "You hit your burn goal!";
            } else {
                burnStatus = (burnGoal - caloriesOut) + " cal left to burn.";
            }
        }

        // Get meals
        List<String> meals = userMealRepository.findByUserIdAndMealDate(userId, today)
                .stream()
                .map(um -> String.format("%s (%s) - %.0f cal",
                        um.getMeal().getFoodName(),
                        um.getMeal().getMealType(),
                        um.getMeal().getCalories()))
                .collect(Collectors.toList());

        // Get workouts
        List<String> workouts = userWorkoutRepository.findByUserIdAndCompletionDate(userId, today)
                .stream()
                .map(uw -> uw.getWorkout().getName())
                .collect(Collectors.toList());

        // Prepare DTO
        TodayDashboardDTO dto = new TodayDashboardDTO();
        dto.setDate(today.toString());
        dto.setCaloriesIn(caloriesIn);
        dto.setCaloriesOut(caloriesOut);
        dto.setNetCalories(caloriesIn - caloriesOut);
        dto.setIntakeGoal(intakeGoal);
        dto.setBurnGoal(burnGoal);
        dto.setIntakeStatus(intakeStatus);
        dto.setBurnStatus(burnStatus);
        dto.setMeals(meals);
        dto.setWorkouts(workouts);

        return dto;
    }
}
