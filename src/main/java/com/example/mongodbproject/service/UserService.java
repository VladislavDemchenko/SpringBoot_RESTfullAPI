package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.dto.UserDto;
import com.example.mongodbproject.exception.IncorrectPasswordException;
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

    @Cacheable(value = "userByLogin", key = "#userRequestArgs.login")
    public UserDto login(User userRequestArgs) {
        UserDto userDto = castToUserDto(userRepository.findByLogin(userRequestArgs.getLogin())
                .orElseThrow(() -> new NoSuchElementException("Login or email is not valid")));

        if(!userDto.password().equals(userRequestArgs.getPassword())){
            throw new IncorrectPasswordException("Password is incorrect");
        }

        return userDto;
    }

    public void updateUserPasswordByEmail(String email, String newPassword) {
        User user = getUserByEmail(email);
        user.setPassword(newPassword);
        this.save(user);
    }


    @Cacheable(value = "usersCache", key = "#email")
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email is unverified"));
    }
    //todo: think about logic of method login - with cast in Class or cast in repository
    public UserDto castToUserDto(User user){
        return new UserDto(user.getLogin(), user.getPassword(), user.getEmail());
    }
}
