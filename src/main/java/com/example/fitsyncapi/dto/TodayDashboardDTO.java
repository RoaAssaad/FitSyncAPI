package com.example.fitsyncapi.dto;

import java.util.List;

public class TodayDashboardDTO {
    private String date;

    private double caloriesIn;
    private double caloriesOut;
    private double netCalories;

    private double intakeGoal;
    private double burnGoal;

    private String intakeStatus;
    private String burnStatus;

    private List<String> meals;
    private List<String> workouts;

    // Getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCaloriesIn() {
        return caloriesIn;
    }

    public void setCaloriesIn(double caloriesIn) {
        this.caloriesIn = caloriesIn;
    }

    public double getCaloriesOut() {
        return caloriesOut;
    }

    public void setCaloriesOut(double caloriesOut) {
        this.caloriesOut = caloriesOut;
    }

    public double getNetCalories() {
        return netCalories;
    }

    public void setNetCalories(double netCalories) {
        this.netCalories = netCalories;
    }

    public double getIntakeGoal() {
        return intakeGoal;
    }

    public void setIntakeGoal(double intakeGoal) {
        this.intakeGoal = intakeGoal;
    }

    public double getBurnGoal() {
        return burnGoal;
    }

    public void setBurnGoal(double burnGoal) {
        this.burnGoal = burnGoal;
    }

    public String getIntakeStatus() {
        return intakeStatus;
    }

    public void setIntakeStatus(String intakeStatus) {
        this.intakeStatus = intakeStatus;
    }

    public String getBurnStatus() {
        return burnStatus;
    }

    public void setBurnStatus(String burnStatus) {
        this.burnStatus = burnStatus;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public List<String> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<String> workouts) {
        this.workouts = workouts;
    }
}
