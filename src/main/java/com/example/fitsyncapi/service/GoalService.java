package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.GoalModel;
import com.example.fitsyncapi.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Optional<GoalModel> getGoalByUserId(int userId) {
        return goalRepository.findByUserId(userId);
    }

    public GoalModel setOrUpdateGoal(int userId, double intakeGoal, double burnGoal) {
        GoalModel goal = goalRepository.findByUserId(userId).orElse(new GoalModel());
        goal.setUserId(userId);
        goal.setCaloriesInGoal(intakeGoal);
        goal.setCaloriesBurnGoal(burnGoal);
        return goalRepository.save(goal);
    }

    public void deleteGoalByUserId(int userId) {
        goalRepository.deleteByUserId(userId);
    }
}
