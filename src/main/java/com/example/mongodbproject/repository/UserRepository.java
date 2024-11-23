package com.example.mongodbproject.repository;

import com.example.mongodbproject.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
