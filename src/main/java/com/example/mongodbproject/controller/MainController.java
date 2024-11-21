package com.example.mongodbproject.controller;

import com.example.mongodbproject.Entity.BoobsSize;
import com.example.mongodbproject.Entity.Marina;
import com.example.mongodbproject.Entity.Mood;
import com.example.mongodbproject.Entity.StringContext;
import com.example.mongodbproject.service.MarinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/marina")
public class MainController {

    private final MarinaService marinaService;

    @PostMapping("/crateMarina")
    public Marina createMarina(@RequestBody Marina marina) {
        marinaService.createMarina(marina);
        return marina;
    }
    @PutMapping("/changeMarinaMood/{id}")
    public Marina changeMarinaMood(@PathVariable String id, @RequestBody Mood mood) {
        marinaService.changeMarinaMood(id, mood);
        return marinaService.changeMarinaMood(id, mood);
    }
    @PutMapping("/changeMarinaBoobs")
    public ResponseEntity<String> changeMarinaBoobs(@RequestParam Integer id, BoobsSize boobsSize) {
        return new ResponseEntity<>(marinaService.changeMarinaBoobs(id, boobsSize), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Marina> getMatina(){
        return marinaService.getAllMatina();
    }

    public void deleteMarinaEntity(){

    }
}
