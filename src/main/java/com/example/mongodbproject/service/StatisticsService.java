package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.Statistics;
import com.example.mongodbproject.repository.StatisticRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticRepository statisticRepository;

    public void loadStatisticsFromFile() throws Exception {
        // Завантажуємо JSON з файлу
        InputStream inputStream = new ClassPathResource("test_report.json").getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> statisticsData = objectMapper.readValue(inputStream, Map.class);

        // Створюємо статистику і зберігаємо в MongoDB
        Statistics statistics = new Statistics();
        statistics.setData(statisticsData);
        statisticRepository.save(statistics);
    }

    @Cacheable("statsByDate")
    public List<Statistics> getStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
//        return statisticRepository.findAll().stream()
//                .filter(stat -> /* логіка для фільтрації по даті */)
//                .collect(Collectors.toList());
        return null;
    }

    @Cacheable("statsByAsin")
    public List<Statistics> getStatisticsByAsin(List<String> asins) {
//        return statisticRepository.findAll().stream()
//                .filter(stat -> /* логіка для фільтрації по ASIN */)
//                .collect(Collectors.toList());
        return null;
    }

    public Object getAllStatistic() {
        return null;
    }
}
