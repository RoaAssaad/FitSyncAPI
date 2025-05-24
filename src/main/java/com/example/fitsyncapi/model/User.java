package com.example.fitsyncapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private int age;
    private String gender;
    private double weight;
    private double height;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserMealModel> userMeals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserWorkoutModel> userWorkouts;

    // Constructors
    public User() {}

    public User(String name, String email, String password, int age, String gender, double weight, double height) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<UserMealModel> getUserMeals() {
        return userMeals;
    }

    public void setUserMeals(List<UserMealModel> userMeals) {
        this.userMeals = userMeals;
    }

    public List<UserWorkoutModel> getUserWorkouts() {
        return userWorkouts;
    }

    public void setUserWorkouts(List<UserWorkoutModel> userWorkouts) {
        this.userWorkouts = userWorkouts;
    }
}
