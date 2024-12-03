package com.example.mongodbproject.mongo.repository;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
