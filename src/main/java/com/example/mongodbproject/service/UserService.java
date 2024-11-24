package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.repository.UserRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String responseMassage = "";

    public String saveUser(User userLoginData) {
        return userRepository.insert(userLoginData).toString();

    }

    public String login(User userLoginData) {
        return userRepository.findByLogin(userLoginData).toString();
    }
}
