package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.DailySummaryModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.repository.DailySummaryRepository;
import com.example.fitsyncapi.repository.UserMealRepository;
import com.example.fitsyncapi.repository.UserWorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailySummaryService {

    private final DailySummaryRepository dailySummaryRepository;
    private final UserMealRepository userMealRepository;
    private final UserWorkoutRepository userWorkoutRepository;

    public DailySummaryService(DailySummaryRepository dailySummaryRepository,
                               UserMealRepository userMealRepository,
                               UserWorkoutRepository userWorkoutRepository) {
        this.dailySummaryRepository = dailySummaryRepository;
        this.userMealRepository = userMealRepository;
        this.userWorkoutRepository = userWorkoutRepository;
    }

    public DailySummaryModel calculateAndSave(User user, LocalDate date) {
        // Calculate calories consumed from meals
        Double totalCaloriesIn = userMealRepository.sumCaloriesByUserIdAndMealDate(user.getId(), date);
        if (totalCaloriesIn == null) totalCaloriesIn = 0.0;

        // Calculate calories burned from workouts
        Double totalCaloriesOut = userWorkoutRepository.sumCaloriesBurnedByUserIdAndDate(user.getId(), date);
        if (totalCaloriesOut == null) totalCaloriesOut = 0.0;

        // Fetch existing summary or create new one
        Optional<DailySummaryModel> optionalSummary = dailySummaryRepository.findByUserIdAndDate(user.getId(), date);
        DailySummaryModel summary = optionalSummary.orElse(new DailySummaryModel());

        summary.setUserId(user.getId());
        summary.setDate(date);
        summary.setCaloriesConsumed(totalCaloriesIn);
        summary.setCaloriesBurned(totalCaloriesOut);

        return dailySummaryRepository.save(summary);
    }

    public Optional<DailySummaryModel> getByUserAndDate(User user, LocalDate date) {
        return dailySummaryRepository.findByUserIdAndDate(user.getId(), date);
    }
}
