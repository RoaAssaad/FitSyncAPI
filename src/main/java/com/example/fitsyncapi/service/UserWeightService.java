package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.UserWeightModel;
import com.example.fitsyncapi.repository.UserWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserWeightService {

    private final UserWeightRepository userWeightRepository;

    @Autowired
    public UserWeightService(UserWeightRepository userWeightRepository) {
        this.userWeightRepository = userWeightRepository;
    }

    /**
     * Logs or updates a user's weight for a specific date.
     */
    public UserWeightModel saveOrUpdateWeight(int userId, LocalDate date, double weight) {
        UserWeightModel weightEntry = new UserWeightModel(userId, date, weight);
        return userWeightRepository.save(weightEntry); // save() handles both insert and update based on PK
    }

    /**
     * Gets all logged weights for a user, ordered by date.
     */
    public List<UserWeightModel> getUserWeights(int userId) {
        return userWeightRepository.findByUserIdOrderByDateAsc(userId);
    }

    /**
     * Fetches a specific weight entry by user and date, if needed.
     */
    public Optional<UserWeightModel> getWeightByDate(int userId, LocalDate date) {
        return userWeightRepository.findByUserIdAndDate(userId, date);
    }
}
