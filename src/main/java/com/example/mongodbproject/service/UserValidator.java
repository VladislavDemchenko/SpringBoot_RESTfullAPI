package com.example.mongodbproject.service;

import com.example.mongodbproject.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;


    @Cacheable(value = "userValidationCache", key = "#email != null ? #email : #login")
    public void validateUser(String login, String email, boolean checkExistence) {
        if (email != null) {
            userRepository.findByEmail(email).ifPresent(user -> {
                if (!checkExistence) {
                    throw new IllegalArgumentException("Email already exists");
                }
            });
            if (checkExistence && userRepository.findByEmail(email).isEmpty()) {
                throw new IllegalArgumentException("No account found with this email");
            }
        }

        if (login != null && !checkExistence) {
            userRepository.findByLogin(login).ifPresent(user -> {
                throw new IllegalArgumentException("Login already exists");
            });
        }
    }
}

