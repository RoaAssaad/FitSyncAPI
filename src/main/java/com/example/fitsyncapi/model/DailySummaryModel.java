package com.example.fitsyncapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_summary")
public class DailySummaryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "calories_consumed", nullable = false)
    private double caloriesConsumed;

    @Column(name = "calories_burned", nullable = false)
    private double caloriesBurned;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public DailySummaryModel() {
    }

    public DailySummaryModel(int userId, double caloriesConsumed, double caloriesBurned, LocalDate date) {
        this.userId = userId;
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
