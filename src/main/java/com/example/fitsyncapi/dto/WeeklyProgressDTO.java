package com.example.fitsyncapi.dto;

import java.time.LocalDate;

public class WeeklyProgressDTO {
    private LocalDate date;
    private double caloriesConsumed;
    private double caloriesBurned;

    public WeeklyProgressDTO() {
    }

    public WeeklyProgressDTO(LocalDate date, double caloriesConsumed, double caloriesBurned) {
        this.date = date;
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(double caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
