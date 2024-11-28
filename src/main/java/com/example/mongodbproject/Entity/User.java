package com.example.mongodbproject.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    private String password;
    
    @Indexed(unique = true)
    private String email;
}
