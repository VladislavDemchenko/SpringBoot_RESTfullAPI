package com.example.mongodbproject.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documentation")
@Setter
@Getter
public class StringContext {
    @Id
    private String id;
    private String context;
}
