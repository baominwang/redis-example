package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DescribePersonResponse implements Serializable {
    private String id;
    private String firstname;
    private String lastname;
}
