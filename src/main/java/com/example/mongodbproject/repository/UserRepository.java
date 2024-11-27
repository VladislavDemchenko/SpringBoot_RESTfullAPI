package com.example.mongodbproject.repository;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<UserDto> findByLogin(String login);
}
