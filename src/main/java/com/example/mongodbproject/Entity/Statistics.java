package com.example.mongodbproject.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.util.Map;

@Document(collation = "statistic")
@Getter
@Setter
@NoArgsConstructor
@RedisHash("Statistic")
public class Statistics {
    @Id
    private String id;
    private Map<String, Object> data;
}
