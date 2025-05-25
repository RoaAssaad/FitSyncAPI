package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.model.WorkoutModel;
import com.example.fitsyncapi.model.UserWorkoutModel;
import com.example.fitsyncapi.repository.UserWorkoutRepository;
import com.example.fitsyncapi.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserWorkoutRepository userWorkoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserWorkoutRepository userWorkoutRepository) {
        this.workoutRepository = workoutRepository;
        this.userWorkoutRepository = userWorkoutRepository;
    }

    public List<WorkoutModel> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Optional<WorkoutModel> getWorkoutById(int id) {
        return workoutRepository.findById(id);
    }

    // Always create a new workout — no longer depends on name uniqueness
    public WorkoutModel createWorkout(String name, int duration) {
        WorkoutModel workout = new WorkoutModel();
        workout.setName(name);
        workout.setDuration(duration);
        return workoutRepository.save(workout);
    }

    // Update existing workout by ID
    public WorkoutModel updateWorkoutById(int id, String name, int duration) {
        WorkoutModel workout = workoutRepository.findById(id).orElseThrow();
        workout.setName(name);
        workout.setDuration(duration);
        return workoutRepository.save(workout);
    }

    public void deleteWorkoutById(int id) {
        workoutRepository.deleteById(id);
    }

    @Transactional
    public UserWorkoutModel logUserWorkout(User user, WorkoutModel workout, LocalDate date) {
        UserWorkoutModel log = new UserWorkoutModel();
        log.setUser(user);
        log.setWorkout(workout);
        log.setCompletionDate(date);
        return userWorkoutRepository.save(log);
    }

    public List<UserWorkoutModel> getUserWorkoutsForDate(User user, LocalDate date) {
        return userWorkoutRepository.findByUserAndCompletionDate(user, date);
    }

    public void deleteUserWorkoutById(int id) {
        userWorkoutRepository.deleteById(id);
    }
}
