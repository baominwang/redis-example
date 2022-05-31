package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateUserResponse implements Serializable {
    private String userId;
}
