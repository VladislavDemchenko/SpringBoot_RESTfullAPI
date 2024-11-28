package com.example.mongodbproject.controller;

import com.example.mongodbproject.Entity.User;
import com.example.mongodbproject.service.TemporaryDataServer;
import com.example.mongodbproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    private final TemporaryDataServer temporaryDataServer;
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody User userRegisterData){

        temporaryDataServer.saveTemporaryData(userRegisterData);

        return new ResponseEntity<>("Pre-verification data saved", HttpStatus.ACCEPTED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequestArgs){
        return new ResponseEntity<>(userService.login(userRequestArgs), HttpStatus.ACCEPTED);
    }
    @PutMapping
    public ResponseEntity<?> updateUserPasswordByEmail(@RequestParam String email, String newPassword){
        return new ResponseEntity<>(userService.updateUserPasswordByEmail(email, newPassword), HttpStatus.ACCEPTED);
    }
}
