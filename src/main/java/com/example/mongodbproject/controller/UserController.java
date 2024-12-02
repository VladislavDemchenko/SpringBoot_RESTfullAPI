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
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    private final TemporaryDataService temporaryDataService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody User userRegisterData){

        temporaryDataService.handleValidUniqueLoginAndEmail(userRegisterData.getLogin(), userRegisterData.getEmail());
        temporaryDataService.saveTemporaryData(userRegisterData, "save");

        return new ResponseEntity<>("Pre-verification data saved", HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequestArgs){
        return new ResponseEntity<>(userService.login(userRequestArgs), HttpStatus.ACCEPTED);
    }

    //todo: test it
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam String newPassword, @RequestParam String email){

        temporaryDataService.foundEmail(email);
        temporaryDataService.saveTemporaryData(new User(email, newPassword), "updatePassword");

        return new ResponseEntity<>("Pre-verification data saved", HttpStatus.ACCEPTED);
    }
}
