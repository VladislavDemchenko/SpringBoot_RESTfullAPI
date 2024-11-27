package com.example.mongodbproject.repository;

import com.example.mongodbproject.Entity.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticRepository extends MongoRepository<Statistics, String> {
}
