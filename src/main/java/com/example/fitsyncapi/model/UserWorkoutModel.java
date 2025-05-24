package com.example.fitsyncapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "User_Workouts")
public class UserWorkoutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonBackReference
    private WorkoutModel workout;

    @Column(name = "completion_date", nullable = false)
    private LocalDate completionDate;

    // Constructors
    public UserWorkoutModel() {}

    public UserWorkoutModel(User user, WorkoutModel workout, LocalDate completionDate) {
        this.user = user;
        this.workout = workout;
        this.completionDate = completionDate;
    }

    // Getters and Setters

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

    public WorkoutModel getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutModel workout) {
        this.workout = workout;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
