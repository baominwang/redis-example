package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DescribePersonRequest implements Serializable {
    private String firstname;
}
