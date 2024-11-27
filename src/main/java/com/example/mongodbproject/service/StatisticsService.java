package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
//    private final StatisticRepository statisticRepository;

    public void loadStatisticsFromFile() throws Exception {
//         Завантажуємо JSON з файлу
//        InputStream inputStream = new ClassPathResource("test_report.json").getInputStream();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> statisticsData = objectMapper.readValue(inputStream, Map.class);
//
//         Створюємо статистику і зберігаємо в MongoDB
//        Statistics statistics = new Statistics();
//        statistics.setData(statisticsData);
//        statisticRepository.save(statistics);
    }

//    @Cacheable("statsByDate")
    public List<Statistics> getStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
//        return statisticRepository.findAll().stream()
//                .filter(stat -> /* логіка для фільтрації по даті */)
//                .collect(Collectors.toList());
        return null;
    }

//    @Cacheable("statsByAsin")
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
