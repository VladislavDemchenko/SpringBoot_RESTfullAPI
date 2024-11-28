package com.example.mongodbproject.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "email_verification_tokens")
@Getter
@Setter
public class TemporaryMemory {
    @Id
    private String id;
    private String token;
    private String userId;

    private LocalDateTime expiryDate;

    private User user;

}

