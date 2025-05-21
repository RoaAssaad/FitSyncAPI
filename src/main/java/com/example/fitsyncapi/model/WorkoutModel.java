package com.example.fitsyncapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "workouts")
public class WorkoutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int duration; // total calories burned or minutes

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<UserWorkoutModel> userWorkouts;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<UserWorkoutModel> getUserWorkouts() {
        return userWorkouts;
    }

    public void setUserWorkouts(List<UserWorkoutModel> userWorkouts) {
        this.userWorkouts = userWorkouts;
    }
}
