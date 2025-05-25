package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.UserWeightModel;
import com.example.fitsyncapi.model.UserWeightModel.UserWeightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserWeightRepository extends JpaRepository<UserWeightModel, UserWeightId> {

    // Fetch weights by user, ordered by date
    List<UserWeightModel> findByUserIdOrderByDateAsc(int userId);

    //  fetch a specific day's weight for a user
    Optional<UserWeightModel> findByUserIdAndDate(int userId, LocalDate date);
}
