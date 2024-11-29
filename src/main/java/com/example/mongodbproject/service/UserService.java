package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.dto.UserDto;
import com.example.mongodbproject.exception.IncorrectPasswordException;
import com.example.mongodbproject.mongo.repository.TemporaryDataRepository;
import com.example.mongodbproject.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void save(User user) {
        userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#userRequestArgs.login")
    public UserDto login(User userRequestArgs) {
        UserDto userDto = userRepository.findByLogin(userRequestArgs.getLogin())
                .orElseThrow(() -> new NoSuchElementException("Login or email is not valid"));

        if(!userDto.password().equals(userRequestArgs.getPassword())){
            throw new IncorrectPasswordException("Password is incorrect");
        }

        return userDto;
    }

    public String updateUserPasswordByEmail(String email, String newPassword) {
        return null;
    }

}
