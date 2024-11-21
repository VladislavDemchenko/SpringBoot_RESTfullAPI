package com.example.mongodbproject.service;

import com.example.mongodbproject.Entity.StringContext;
import com.example.mongodbproject.repository.StringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContextService {
    @Autowired
    private StringRepository repository;

    public List<StringContext> getAllEntities() {
        return repository.findAll();
    }

    public StringContext createEntity(StringContext entity) {
        return repository.save(entity);
    }

    public StringContext getEntityByContext(String context) {
        return repository.findByContext(context);
    }

    public void deleteEntity(String id) {
        repository.deleteById(id);
    }
}
