package com.example.mongodbproject.repository;

import com.example.mongodbproject.Entity.StringContext;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StringRepository extends MongoRepository<StringContext, String> {
    StringContext findByContext(String context);
}
