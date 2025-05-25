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
    @GetMapping("/all")
    public ResponseEntity<List<WorkoutModel>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    // 2. Create a new workout
    @PostMapping
    public ResponseEntity<WorkoutModel> createWorkout(@RequestParam String name,
                                                      @RequestParam int duration) {
        WorkoutModel workout = workoutService.createWorkout(name, duration);
        return ResponseEntity.ok(workout);
    }

    // 3. Update a workout by ID
    @PutMapping("/update")
    public ResponseEntity<WorkoutModel> updateWorkoutById(@RequestParam int id,
                                                          @RequestParam String name,
                                                          @RequestParam int duration) {
        WorkoutModel workout = workoutService.updateWorkoutById(id, name, duration);
        return ResponseEntity.ok(workout);
    }

    // 4. Delete a workout by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWorkoutById(@PathVariable int id) {
        workoutService.deleteWorkoutById(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Log a workout for a user
    @PostMapping("/log")
    public ResponseEntity<UserWorkoutModel> logWorkout(@RequestParam int userId,
                                                       @RequestParam int workoutId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        WorkoutModel workout = workoutService.getWorkoutById(workoutId).orElseThrow();
        UserWorkoutModel logged = workoutService.logUserWorkout(user, workout, date);
        return ResponseEntity.ok(logged);
    }

    // 6. Get logged workouts for user on specific date
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserWorkoutModel>> getUserWorkouts(@PathVariable int userId,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userService.getUserById(userId).orElseThrow();
        List<UserWorkoutModel> list = workoutService.getUserWorkoutsForDate(user, date);
        return ResponseEntity.ok(list);
    }

    // 7. Delete a specific user workout log
    @DeleteMapping("/user-log/{id}")
    public ResponseEntity<Void> deleteUserWorkoutLog(@PathVariable int id) {
        workoutService.deleteUserWorkoutById(id);
        return ResponseEntity.noContent().build();
    }
}
