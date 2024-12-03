package com.example.mongodbproject.controller;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.service.TemporaryDataService;
import com.example.mongodbproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailVerificationController {

    private final TemporaryDataService verificationService;

    private final UserService userService;

    @GetMapping("save/verify-email")
    public ResponseEntity<?> saveVerifyEmail(@RequestParam String token) {
            return verificationService.responseValidationTokenHandler(token, () ->{
                userService.save(verificationService.findUserByToken(token));
                return new ResponseEntity<>("Verification successfully", HttpStatus.CREATED);
            });
    }

    @GetMapping("updatePassword/verify-email")
    public ResponseEntity<?> updatePasswordVerifyEmail(@RequestParam String token) {
        return verificationService.responseValidationTokenHandler(token, () -> {
            User user = verificationService.findUserByToken(token);
            userService.updateUserPasswordByEmail(user.getEmail(), user.getPassword());
            return new ResponseEntity<>("Verification successfully", HttpStatus.CREATED);
        });
    }
}

