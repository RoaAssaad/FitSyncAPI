package com.example.fitsyncapi.controller;

import com.example.fitsyncapi.dto.UserUpdateDTO;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "User Profile Controller", description = "View and update user profile")
public class UserProfileController {

    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile information for a user")
    public ResponseEntity<User> getUserProfile(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile information for a user")
    public ResponseEntity<User> updateUserProfile(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateDTO updatedProfile
    ) {
        try {
            User updatedUser = userService.updateUserFields(id, updatedProfile);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
