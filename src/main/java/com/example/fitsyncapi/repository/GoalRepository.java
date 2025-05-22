package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.GoalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<GoalModel, Integer> {

    // Find goals by user ID
    Optional<GoalModel> findByUserId(int userId);

    // Delete goals by user ID
    void deleteByUserId(int userId);
}
