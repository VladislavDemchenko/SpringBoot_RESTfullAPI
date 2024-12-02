package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.TemporaryMemory;
import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.mongo.repository.TemporaryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporaryDataService {
    private final TemporaryDataRepository tokenRepository;
    private final EmailService emailService;
    private final UserValidator userValidator;

    public String generateVerificationToken() {
       return UUID.randomUUID().toString();
    }

    @Cacheable(value = "validToken", key = "#token")
    public boolean validateToken(String token) {
        // Знаходимо токен у базі
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now())) // Перевіряємо, чи не прострочений
                .isPresent();
    }
    @Cacheable(value = "token", key = "#token")
    public User findUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("token out of timeline ")) // verification code are done for time
                .getUser();
    }

    @Cacheable(value = "token", key = "#token")
    public void deleteToken(String token) {
        tokenRepository.findByToken(token)
                .ifPresent(tokenRepository::delete);
    }
/// ///////////////////////////////////refact every methods with all findByToken
//    @Cacheable(value = "token", key = "#token")
//    public TemporaryMemory findByToken(String token){
//        return tokenRepository.findByToken(token);
//    }

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
    public void handleValidUniqueLoginAndEmail(String login, String email) {
        userValidator.validateUser(login, email, false);
    }

    public void foundEmail(String email) {
        userValidator.validateUser(null, email, true);
    }

}
