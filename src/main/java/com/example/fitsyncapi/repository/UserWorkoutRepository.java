package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.UserWorkoutModel;
import com.example.fitsyncapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserWorkoutRepository extends JpaRepository<UserWorkoutModel, Integer> {
    List<UserWorkoutModel> findByUser(User user);
    List<UserWorkoutModel> findByUserAndCompletionDate(User user, LocalDate date);
}
