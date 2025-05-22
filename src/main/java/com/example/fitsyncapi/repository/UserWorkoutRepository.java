package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.model.UserWorkoutModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserWorkoutRepository extends JpaRepository<UserWorkoutModel, Integer> {

    List<UserWorkoutModel> findByUser(User user);

    List<UserWorkoutModel> findByUserAndCompletionDate(User user, LocalDate date);

    // Custom query to sum burned calories for daily summary
    @Query("SELECT SUM(w.duration) " +
            "FROM UserWorkoutModel uw " +
            "JOIN uw.workout w " +
            "WHERE uw.user.id = :userId AND uw.completionDate = :date")
    Double sumCaloriesBurnedByUserIdAndDate(int userId, LocalDate date);


    List<UserWorkoutModel> findByUserIdAndCompletionDate(int userId, LocalDate date);
}
