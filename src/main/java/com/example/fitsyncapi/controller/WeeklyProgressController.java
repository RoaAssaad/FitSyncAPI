package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.dto.WeeklyProgressDTO;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.service.WeeklyProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/weekly-progress")
public class WeeklyProgressController {

    private final WeeklyProgressService weeklyProgressService;

    public WeeklyProgressController(WeeklyProgressService weeklyProgressService) {
        this.weeklyProgressService = weeklyProgressService;
    }

    @GetMapping
    public ResponseEntity<List<WeeklyProgressDTO>> getWeeklyProgress(@RequestParam("userId") int userId) {
        // In real-world app, you'd extract user from security context (e.g., JWT). Here, userId is passed directly.
        User user = new User();
        user.setId(userId);

        List<WeeklyProgressDTO> progress = weeklyProgressService.getWeeklyProgress(user);
        return ResponseEntity.ok(progress);
    }
}
