package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.BoobsSize;
import com.example.mongodbproject.Entity.Marina;
import com.example.mongodbproject.Entity.Mood;
import com.example.mongodbproject.repository.MarinaRepository;
import com.example.mongodbproject.repository.StringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarinaService {
//
    private final MarinaRepository marinaRepository;
    private final StringRepository stringRepository;
//
    public void createMarina(Marina marina){
        marinaRepository.save(marina);
    }
    public Marina changeMarinaMood(String id, Mood mood){

        return marinaRepository.changeMarinaMoodById(id, mood);
    }
    public String changeMarinaBoobs(Integer id, BoobsSize boobsSize){
//        marinaRepository.changeMarinaCupSizeById(id, boobsSize);
        return "Have been changed";
    }
    public List<Marina> getAllMatina(){
        return marinaRepository.findAll();
    }
    public void deleteMarinaEntity(){

    }

}
