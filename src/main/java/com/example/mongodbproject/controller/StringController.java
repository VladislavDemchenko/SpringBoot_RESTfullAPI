package com.example.mongodbproject.controller;

import com.example.mongodbproject.Entity.StringContext;
import com.example.mongodbproject.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class StringController {

    @Autowired
    private ContextService service;

    @GetMapping
    public List<StringContext> getAll() {
        return service.getAllEntities();
    }

    @PostMapping
    public StringContext create(@RequestBody StringContext entity) {
        return service.createEntity(entity);
    }

    @GetMapping("/{name}")
    public StringContext getByName(@PathVariable String name) {
        return service.getEntityByContext(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteEntity(id);
    }
}
