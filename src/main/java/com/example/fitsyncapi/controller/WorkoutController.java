package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.model.WorkoutModel;
import com.example.fitsyncapi.model.UserWorkoutModel;
import com.example.fitsyncapi.service.UserService;
import com.example.fitsyncapi.service.WorkoutService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    // 1. Get all workouts
    @GetMapping
    public List<WorkoutModel> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    // 2. Create or update a workout
    @PostMapping
    public ResponseEntity<WorkoutModel> createOrUpdateWorkout(@RequestParam String name,
                                                              @RequestParam int duration) {
        WorkoutModel workout = workoutService.createOrUpdateWorkout(name, duration);
        return ResponseEntity.ok(workout);
    }

    // 3. Delete workout by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable int id) {
        workoutService.deleteWorkoutById(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Log a workout for a user
    @PostMapping("/log")
    public ResponseEntity<UserWorkoutModel> logWorkout(@RequestParam int userId,
                                                       @RequestParam int workoutId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        WorkoutModel workout = workoutService.getWorkoutById(workoutId).orElseThrow();
        UserWorkoutModel logged = workoutService.logUserWorkout(user, workout, date);
        return ResponseEntity.ok(logged);
    }

    // 5. Get workouts logged by a user on a date
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserWorkoutModel>> getUserWorkouts(@PathVariable int userId,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        List<UserWorkoutModel> list = workoutService.getUserWorkoutsForDate(user, date);
        return ResponseEntity.ok(list);
    }

    // 6. Delete a specific user workout record
    @DeleteMapping("/user-log/{id}")
    public ResponseEntity<Void> deleteUserWorkoutLog(@PathVariable int id) {
        workoutService.deleteUserWorkoutById(id);
        return ResponseEntity.noContent().build();
    }
    // Get all workouts
    @GetMapping("/all")
    public ResponseEntity<List<WorkoutModel>> getAll() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

}
