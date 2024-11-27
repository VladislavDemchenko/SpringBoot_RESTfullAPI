package com.example.mongodbproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTask {

    @Autowired
    private StatisticsService statisticsService;

//    @Scheduled(fixedRate = 300000)  // Оновлення кожні 5 хвилин
    public void updateStatistics() {
        try {
            statisticsService.loadStatisticsFromFile(); // Оновлюємо статистику
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
