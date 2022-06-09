package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreatePersonRequest implements Serializable {
    private String firstname;

    private String lastname;
}
