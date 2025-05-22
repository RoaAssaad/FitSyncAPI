package com.example.fitsyncapi.service;

import com.example.fitsyncapi.model.DailySummaryModel;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.repository.DailySummaryRepository;
import com.example.fitsyncapi.dto.WeeklyProgressDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeeklyProgressService {

    private final DailySummaryRepository dailySummaryRepository;

    public WeeklyProgressService(DailySummaryRepository dailySummaryRepository) {
        this.dailySummaryRepository = dailySummaryRepository;
    }

    public List<WeeklyProgressDTO> getWeeklyProgress(User user) {
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6); // past 7 days including today

        List<DailySummaryModel> summaries = dailySummaryRepository
                .findAllByUserIdAndDateBetweenOrderByDate(user.getId(), weekAgo, today);

        List<WeeklyProgressDTO> progressList = new ArrayList<>();

        for (DailySummaryModel summary : summaries) {
            WeeklyProgressDTO dto = new WeeklyProgressDTO();
            dto.setDate(summary.getDate());
            dto.setCaloriesConsumed(summary.getCaloriesConsumed());
            dto.setCaloriesBurned(summary.getCaloriesBurned());
            progressList.add(dto);
        }

        return progressList;
    }
}
