package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.MealModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.model.UserMealModel;
import com.example.fitsyncapi.repository.MealRepository;
import com.example.fitsyncapi.repository.UserMealRepository;
import jakarta.transaction.Transactional;
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

    public MealModel createOrUpdateMeal(String foodName, double calories, String mealType) {
        MealModel meal = mealRepository.findByFoodName(foodName)
                .orElse(new MealModel());

        meal.setFoodName(foodName);
        meal.setCalories(calories);
        meal.setMealType(mealType);

        return mealRepository.save(meal);
    }

    public void deleteMealById(int id) {
        mealRepository.deleteById(id);
    }

    @Transactional
    public UserMealModel logUserMeal(User user, MealModel meal, LocalDate date) {
        UserMealModel log = new UserMealModel();
        log.setUser(user);
        log.setMeal(meal);
        log.setMealDate(date);
        return userMealRepository.save(log);
    }

    public List<UserMealModel> getUserMealsForDate(int userId, LocalDate date) {
        return userMealRepository.findByUserIdAndMealDate(userId, date);
    }

    public void deleteUserMealById(int id) {
        userMealRepository.deleteById(id);
    }
}
