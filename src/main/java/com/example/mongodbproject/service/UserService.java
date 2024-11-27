package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.dto.UserDto;
import com.example.mongodbproject.exception.IncorrectPasswordException;
import com.example.mongodbproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String saveUser(User userLoginData) {
        return userRepository.save(userLoginData).toString();
    }

    public UserDto login(User userRequestArgs) {
        UserDto userDto = userRepository.findByLogin(userRequestArgs.getLogin())
                .orElseThrow(() -> new NoSuchElementException("User with login - [" + userRequestArgs.getLogin() + "] not found"));

        if(!userDto.password().equals(userRequestArgs.getPassword())){
            throw new IncorrectPasswordException("Password is incorrect");
        }

        return userDto;
    }
}
