package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.WorkoutModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<WorkoutModel, Integer> {
    Optional<WorkoutModel> findByName(String name);
    boolean existsByName(String name);
}
