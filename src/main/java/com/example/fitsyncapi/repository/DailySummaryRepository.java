package com.example.fitsyncapi.repository;

import com.example.fitsyncapi.model.DailySummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummaryModel, Integer> {

    Optional<DailySummaryModel> findByUserIdAndDate(int userId, LocalDate date);

    List<DailySummaryModel> findAllByUserIdAndDateBetweenOrderByDate(int userId, LocalDate startDate, LocalDate endDate);
}
