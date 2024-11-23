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

    public String saveUser(User user) {
        try {
            responseMassage = userRepository.save(user).toString();
        }catch (MongoWriteException e){
            return "This login already exist!";
        }
        return responseMassage;
    }
}
