package com.example.mongodbproject.dto;

import java.io.Serializable;

public record UserDto(String login, String password) implements Serializable {
}
