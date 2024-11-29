package com.example.mongodbproject.controller;

import com.example.mongodbproject.service.TemporaryDataServer;
import com.example.mongodbproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailVerificationController {

    private final TemporaryDataServer verificationService;

    private final UserService userService;

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        if (verificationService.validateToken(token)) {

            userService.save(verificationService.findUserByToken(token));
            verificationService.deleteToken(token);

            return new ResponseEntity<>("Verification successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Verification code", HttpStatus.BAD_REQUEST);
        }
    }
}

