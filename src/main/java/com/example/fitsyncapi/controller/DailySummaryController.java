package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.model.DailySummaryModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.service.DailySummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/summary")
public class DailySummaryController {

    private final DailySummaryService dailySummaryService;

    public DailySummaryController(DailySummaryService dailySummaryService) {
        this.dailySummaryService = dailySummaryService;
    }

    // Endpoint to get summary for a user and date
    @GetMapping
    public ResponseEntity<DailySummaryModel> getSummaryByUserAndDate(
            @RequestParam int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        User user = new User();
        user.setId(userId);

        Optional<DailySummaryModel> summary = dailySummaryService.getByUserAndDate(user, date);
        return summary.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to trigger calculation and update of summary for a user and date
    @PostMapping("/calculate")
    public ResponseEntity<DailySummaryModel> calculateAndSaveSummary(
            @RequestParam int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        User user = new User();
        user.setId(userId);

        DailySummaryModel savedSummary = dailySummaryService.calculateAndSave(user, date);
        return ResponseEntity.ok(savedSummary);
    }
}
