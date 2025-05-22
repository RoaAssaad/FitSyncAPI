package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.GoalModel;
import com.example.fitsyncapi.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    // 1. Get goals for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<GoalModel> getGoalByUserId(@PathVariable int userId) {
        Optional<GoalModel> goal = goalService.getGoalByUserId(userId);
        return goal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 2. Set or update goals
    @PostMapping
    public ResponseEntity<GoalModel> setOrUpdateGoal(@RequestParam int userId,
                                                     @RequestParam double caloriesInGoal,
                                                     @RequestParam double caloriesBurnGoal) {
        GoalModel updated = goalService.setOrUpdateGoal(userId, caloriesInGoal, caloriesBurnGoal);
        return ResponseEntity.ok(updated);
    }

    // 3. Delete goal for a user
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteGoalByUserId(@PathVariable int userId) {
        goalService.deleteGoalByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
