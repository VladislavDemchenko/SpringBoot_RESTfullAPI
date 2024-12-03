package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.TemporaryMemory;
import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.mongo.repository.TemporaryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TemporaryDataService {
    private final TemporaryDataRepository tokenRepository;
    private final EmailService emailService;
    private final UserValidator userValidator;

    public String generateVerificationToken() {
       return UUID.randomUUID().toString();
    }

    public boolean validateToken(String token) {
        // Знаходимо токен у базі
        return findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now())) // Перевіряємо, чи не прострочений
                .isPresent();
    }
    public User findUserByToken(String token) {
        return findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("token out of timeline ")) // verification code are done for time
                .getUser();
    }

    public void deleteTemporaryData(String token) {
        findByToken(token)
            .ifPresent(tokenRepository::delete);
    }

    @Cacheable(value = "tokens", key = "#token")
    public Optional<TemporaryMemory> findByToken(String token){
        return tokenRepository.findByToken(token);
    }

    public String saveTemporaryData(User user, String method) {
        // Create new token
        TemporaryMemory temporaryMemory = new TemporaryMemory(generateVerificationToken(),
                LocalDateTime.now().plusHours(24),
                user);

        emailService.sendVerificationEmail(temporaryMemory.getUser().getEmail(),
                temporaryMemory.getToken(), method);

        // Зберігаємо токен у базі
        tokenRepository.save(temporaryMemory);

        return "A verification link has been sent to your email!";
    }

    public void handleValidUniqueLoginAndEmail(String login, String email) {
        userValidator.validateUser(login, email, false);
    }

    public void foundEmail(String email) {
        userValidator.validateUser(null, email, true);
    }

    public ResponseEntity<String> responseValidationTokenHandler(String token, Supplier<ResponseEntity<String>> onSuccess) {
        if (validateToken(token)) {
            try {
                ResponseEntity<String> response = onSuccess.get();
                deleteTemporaryData(token);
                return response;
            } catch (Exception e) {
                return new ResponseEntity<>("Error during token processing: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid Verification code", HttpStatus.BAD_REQUEST);
        }
    }

}
