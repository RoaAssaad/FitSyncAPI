package com.example.fitsyncapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Meals")
public class MealModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    // Constructors
    public MealModel() {
    }

    public MealModel(String foodName, double calories, String mealType) {
        this.foodName = foodName;
        this.calories = calories;
        this.mealType = mealType;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}
