package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.UserWeightModel;
import com.example.fitsyncapi.service.UserWeightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weights")
@Tag(name = "User Weight Controller", description = "Manage user's weight logging and progress")
public class UserWeightController {

    private final UserWeightService userWeightService;

    @Autowired
    public UserWeightController(UserWeightService userWeightService) {
        this.userWeightService = userWeightService;
    }

    @PostMapping
    @Operation(summary = "Log or update today's weight")
    public ResponseEntity<UserWeightModel> logWeight(
            @RequestParam @NotNull Integer userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam @DecimalMin("1.0") Double weight
    ) {
        UserWeightModel saved = userWeightService.saveOrUpdateWeight(userId, date, weight);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get all weight records for a user")
    public ResponseEntity<List<UserWeightModel>> getWeights(@PathVariable int userId) {
        List<UserWeightModel> weights = userWeightService.getUserWeights(userId);
        return ResponseEntity.ok(weights);
    }

    @GetMapping("/{userId}/by-date")
    @Operation(summary = "Get user's weight for a specific date")
    public ResponseEntity<UserWeightModel> getWeightByDate(
            @PathVariable int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        Optional<UserWeightModel> weight = userWeightService.getWeightByDate(userId, date);
        return weight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
