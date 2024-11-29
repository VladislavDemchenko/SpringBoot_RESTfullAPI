package com.example.mongodbproject.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "email_verification_tokens")
@Getter
@Setter
@NoArgsConstructor
public class TemporaryMemory {

    @Id
    private String id;
    private String token;
    private LocalDateTime expiryDate;

    @Indexed(unique = true)
    private User user;

    public TemporaryMemory(String token, LocalDateTime expiryDate, User user){
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}

