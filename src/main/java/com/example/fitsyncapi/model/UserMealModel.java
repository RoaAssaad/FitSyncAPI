package com.example.fitsyncapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "User_Meals")
public class UserMealModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private MealModel meal;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    // Constructors
    public UserMealModel() {
    }

    public UserMealModel(User user, MealModel meal, LocalDate mealDate) {
        this.user = user;
        this.meal = meal;
        this.mealDate = mealDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MealModel getMeal() {
        return meal;
    }

    public void setMeal(MealModel meal) {
        this.meal = meal;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }
}
