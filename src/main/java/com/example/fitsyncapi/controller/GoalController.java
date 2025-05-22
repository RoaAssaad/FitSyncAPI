package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.GoalModel;
import com.example.fitsyncapi.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/goals")
@Tag(name = "Goal Controller", description = "Set, get, and delete calorie goals for users")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get goal for a user by user ID")
    public ResponseEntity<GoalModel> getGoalByUserId(@PathVariable int userId) {
        Optional<GoalModel> goal = goalService.getGoalByUserId(userId);
        return goal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Set or update calorie intake and burn goals for a user")
    public ResponseEntity<GoalModel> setOrUpdateGoal(
            @RequestParam int userId,
            @RequestParam double caloriesInGoal,
            @RequestParam double caloriesBurnGoal) {

        GoalModel updated = goalService.setOrUpdateGoal(userId, caloriesInGoal, caloriesBurnGoal);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Delete goal for a user by user ID")
    public ResponseEntity<Void> deleteGoalByUserId(@PathVariable int userId) {
        goalService.deleteGoalByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
