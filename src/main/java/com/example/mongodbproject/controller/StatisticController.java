package com.example.mongodbproject.controller;

import com.example.mongodbproject.Entity.Statistics;
import com.example.mongodbproject.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticsService statisticsService;
    @GetMapping
    public ResponseEntity<?> getAllStatistic(){
        return new ResponseEntity<>(statisticsService.getAllStatistic(), HttpStatus.OK);
    }

    @Scheduled(fixedRate = 300000)  // Оновлення кожні 5 хвилин
    public void updateStatistics() {
        try {
            statisticsService.loadStatisticsFromFile(); // Оновлюємо статистику
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/by-date")
//    public List<Statistics> getStatisticsByDate(@RequestParam String startDate, @RequestParam String endDate) {
//        return statisticsService.getStatisticsByDateRange(startDate, endDate);
//    }

    @GetMapping("/by-asin")
    public List<Statistics> getStatisticsByAsin(@RequestParam List<String> asins) {
        return statisticsService.getStatisticsByAsin(asins);
    }
}
