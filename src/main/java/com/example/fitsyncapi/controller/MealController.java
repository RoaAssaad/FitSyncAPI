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

    // 1. Create or update a meal
    @PostMapping
    public ResponseEntity<MealModel> createOrUpdateMeal(@RequestParam String foodName,
                                                        @RequestParam double calories,
                                                        @RequestParam String mealType) {
        MealModel meal = mealService.createOrUpdateMeal(foodName, calories, mealType);
        return ResponseEntity.ok(meal);
    }

    // 2. Log a meal for a user
    @PostMapping("/log")
    public ResponseEntity<UserMealModel> logMeal(@RequestParam int userId,
                                                 @RequestParam int mealId,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        MealModel meal = mealService.getMealById(mealId).orElseThrow();
        UserMealModel loggedMeal = mealService.logUserMeal(user, meal, date);
        return ResponseEntity.ok(loggedMeal);
    }

    // 3. View all meals for a user on a specific date
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMealModel>> getMealsByUserAndDate(@PathVariable int userId,
                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<UserMealModel> meals = mealService.getUserMealsForDate(userId, date);
        return ResponseEntity.ok(meals);
    }

    // 4. Delete a logged meal
    @DeleteMapping("/user-log/{id}")
    public ResponseEntity<Void> deleteUserMealLog(@PathVariable int id) {
        mealService.deleteUserMealById(id);
        return ResponseEntity.noContent().build();
    }
}
