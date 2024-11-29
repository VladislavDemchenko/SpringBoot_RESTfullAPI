package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.TemporaryMemory;
import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.mongo.repository.TemporaryDataRepository;
import com.example.mongodbproject.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporaryDataServer {
    private final TemporaryDataRepository tokenRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public String generateVerificationToken() {
       return UUID.randomUUID().toString();
    }

    @Cacheable(value = "token", key = "#token")
    public boolean validateToken(String token) {
        // Знаходимо токен у базі
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now())) // Перевіряємо, чи не прострочений
                .isPresent();
    }
    @Cacheable(value = "token", key = "#token")
    public User findUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow()
                .getUser();
    }


    public void deleteToken(String token) {
        tokenRepository.findByToken(token)
                .ifPresent(tokenRepository::delete);
    }

    @Transactional
    public void saveTemporaryData(User user, String method) {


        // Create new token
        TemporaryMemory temporaryMemory = new TemporaryMemory(generateVerificationToken(),
                LocalDateTime.now().plusHours(24),
                user);

        emailService.sendVerificationEmail(temporaryMemory.getUser().getEmail(),
                temporaryMemory.getToken(), method);

        // Зберігаємо токен у базі
        tokenRepository.save(temporaryMemory);
    }

    public void checkForUniqueValue(User user){
        // check for unique email and login before verification
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Login already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}
