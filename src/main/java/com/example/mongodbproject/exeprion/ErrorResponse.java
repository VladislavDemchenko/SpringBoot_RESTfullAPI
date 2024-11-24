package com.example.mongodbproject.exeprion;

import lombok.*;

import javax.swing.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
}
