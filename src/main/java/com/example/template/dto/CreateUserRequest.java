package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateUserRequest implements Serializable {
    private String firstName;

    private String lastName;
}
