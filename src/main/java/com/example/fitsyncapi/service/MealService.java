package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.MealModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.model.UserMealModel;
import com.example.fitsyncapi.repository.MealRepository;
import com.example.fitsyncapi.repository.UserMealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final UserMealRepository userMealRepository;

    public MealService(MealRepository mealRepository, UserMealRepository userMealRepository) {
        this.mealRepository = mealRepository;
        this.userMealRepository = userMealRepository;
    }

    public List<MealModel> getAllMeals() {
        return mealRepository.findAll();
    }

    public Optional<MealModel> getMealById(int id) {
        return mealRepository.findById(id);
    }

    public MealModel createMeal(String foodName, double calories, String mealType) {
        MealModel meal = new MealModel();
        meal.setFoodName(foodName);
        meal.setCalories(calories);
        meal.setMealType(mealType);
        return mealRepository.save(meal);
    }

    public MealModel updateMeal(int id, String foodName, double calories, String mealType) {
        MealModel meal = mealRepository.findById(id).orElseThrow();
        meal.setFoodName(foodName);
        meal.setCalories(calories);
        meal.setMealType(mealType);
        return mealRepository.save(meal);
    }

    public void deleteMealById(int id) {
        mealRepository.deleteById(id);
    }

    public UserMealModel logUserMeal(User user, MealModel meal, LocalDate date) {
        UserMealModel userMeal = new UserMealModel();
        userMeal.setUser(user);
        userMeal.setMeal(meal);
        userMeal.setMealDate(date);
        return userMealRepository.save(userMeal);
    }

    public List<UserMealModel> getUserMealsForDate(int userId, LocalDate date) {
        return userMealRepository.findByUserIdAndMealDate(userId, date);
    }

    public List<UserMealModel> getUserMealsForDateRange(int userId, LocalDate startDate, LocalDate endDate) {
        return userMealRepository.findMealsByUserIdAndDateRange(userId, startDate, endDate);
    }

    public void deleteUserMealById(int id) {
        userMealRepository.deleteById(id);
    }
}
