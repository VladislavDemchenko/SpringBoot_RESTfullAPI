package com.example.mongodbproject.exception;


public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String massage){
        super(massage);
    }
}
