package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.TemporaryMemory;
import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.mongo.repository.TemporaryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporaryDataServer {
    private final TemporaryDataRepository tokenRepository;
    private final EmailService emailService;

    public String generateVerificationToken() {
       return UUID.randomUUID().toString();
    }

    public boolean validateToken(String token) {
        // Знаходимо токен у базі
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now())) // Перевіряємо, чи не прострочений
                .isPresent();
    }


    public void deleteToken(String token) {
        tokenRepository.findByToken(token)
                .ifPresent(tokenRepository::delete);
    }

    public void saveTemporaryData(User user) {
        // Створюємо новий об'єкт токену
        TemporaryMemory temporaryMemory = new TemporaryMemory();
        temporaryMemory.setToken(generateVerificationToken());
        temporaryMemory.setUser(user);
        temporaryMemory.setExpiryDate(LocalDateTime.now().plusHours(24)); // Дійсний 24 години
        emailService.sendVerificationEmail(temporaryMemory.getUser().getEmail(), temporaryMemory.getToken());
//         Зберігаємо токен у базі
        tokenRepository.save(temporaryMemory);
    }
}
