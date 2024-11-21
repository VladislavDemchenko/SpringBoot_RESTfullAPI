package com.example.mongodbproject.repository;

import com.example.mongodbproject.Entity.BoobsSize;
import com.example.mongodbproject.Entity.Marina;
import com.example.mongodbproject.Entity.Mood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MarinaRepository extends MongoRepository<Marina, String> {
//    Marina changeMarinaMoodById(String id, Mood mood);


    Marina changeMarinaMoodById(String id, Mood mood);

//    void changeMarinaCupSizeById(Integer id, BoobsSize boobsSize);
}
