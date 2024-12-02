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
        if (verificationService.validateToken(token)) {

            userService.save(verificationService.findUserByToken(token));
            verificationService.deleteToken(token);

            return new ResponseEntity<>("Verification successfully" , HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Verification code", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("updatePassword/verify-email")
    public ResponseEntity<?> updatePasswordVerifyEmail(@RequestParam String token) {
        if (verificationService.validateToken(token)) {
            ////////// refactor to extract method like in book catalog

            User user = verificationService.findUserByToken(token);

            userService.updateUserPasswordByEmail(user.getEmail(),
                    user.getPassword());

            verificationService.deleteToken(token);

            return new ResponseEntity<>("Verification successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Verification code", HttpStatus.BAD_REQUEST);
        }
    }


}

