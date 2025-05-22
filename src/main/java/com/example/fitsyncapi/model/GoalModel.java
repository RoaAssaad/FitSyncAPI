package com.example.fitsyncapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Goals")
public class GoalModel {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "calories_in_goal", nullable = false)
    private double caloriesInGoal;

    @Column(name = "calories_burn_goal", nullable = false)
    private double caloriesBurnGoal;

    public GoalModel() {
    }

    public GoalModel(int userId, double caloriesInGoal, double caloriesBurnGoal) {
        this.userId = userId;
        this.caloriesInGoal = caloriesInGoal;
        this.caloriesBurnGoal = caloriesBurnGoal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCaloriesInGoal() {
        return caloriesInGoal;
    }

    public void setCaloriesInGoal(double caloriesInGoal) {
        this.caloriesInGoal = caloriesInGoal;
    }

    public double getCaloriesBurnGoal() {
        return caloriesBurnGoal;
    }

    public void setCaloriesBurnGoal(double caloriesBurnGoal) {
        this.caloriesBurnGoal = caloriesBurnGoal;
    }
}
