package com.example.mongodbproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "marina")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Marina{
    @Id
    private String id;

    @Field(name = "boobSize")
    @Indexed(unique = true)
    private BoobsSize boobs;

    @Indexed(unique = true)
    private Mood mood;

    @Field(name = "age")
    private Integer age;

    private String lastName;
}
