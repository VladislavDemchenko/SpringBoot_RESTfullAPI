package com.example.mongodbproject.mongo.repository;

import com.example.mongodbproject.Entity.TemporaryMemory;
import com.example.mongodbproject.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TemporaryDataRepository extends MongoRepository<TemporaryMemory, String> {
    Optional<TemporaryMemory> findByToken(String token);
    User findUserByToken(String token);
}
