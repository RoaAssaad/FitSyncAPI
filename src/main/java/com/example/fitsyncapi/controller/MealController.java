package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.MealModel;
import com.example.fitsyncapi.model.UserMealModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.service.MealService;
import com.example.fitsyncapi.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    // 1. Create a meal (allows duplicates)
    @PostMapping("/create")
    public ResponseEntity<MealModel> createMeal(@RequestParam String foodName,
                                                @RequestParam double calories,
                                                @RequestParam String mealType) {
        MealModel meal = mealService.createMeal(foodName, calories, mealType);
        return ResponseEntity.ok(meal);
    }

    // 2. Update an existing meal by ID
    @PutMapping("/update")
    public ResponseEntity<MealModel> updateMeal(@RequestParam int id,
                                                @RequestParam String foodName,
                                                @RequestParam double calories,
                                                @RequestParam String mealType) {
        MealModel updated = mealService.updateMeal(id, foodName, calories, mealType);
        return ResponseEntity.ok(updated);
    }

    // 3. Delete a meal by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable int id) {
        mealService.deleteMealById(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Log a meal for a user
    @PostMapping("/log")
    public ResponseEntity<UserMealModel> logMeal(@RequestParam int userId,
                                                 @RequestParam int mealId,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        MealModel meal = mealService.getMealById(mealId).orElseThrow();
        UserMealModel logged = mealService.logUserMeal(user, meal, date);
        return ResponseEntity.ok(logged);
    }

    // 5. View all meals for a user on a specific date
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMealModel>> getMealsByUserAndDate(
            @PathVariable int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(mealService.getUserMealsForDate(userId, date));
    }

    // 6. View meals history (date range)
    @GetMapping("/history")
    public ResponseEntity<List<UserMealModel>> getMealsByUserAndDateRange(
            @RequestParam int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(mealService.getUserMealsForDateRange(userId, startDate, endDate));
    }

    // 7. Delete a user meal log by its ID
    @DeleteMapping("/user-log/{id}")
    public ResponseEntity<Void> deleteUserMealLog(@PathVariable int id) {
        mealService.deleteUserMealById(id);
        return ResponseEntity.noContent().build();
    }

    // 8. Get all meals
    @GetMapping("/all")
    public ResponseEntity<List<MealModel>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }
}
