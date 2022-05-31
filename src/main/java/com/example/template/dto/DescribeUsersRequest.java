package com.example.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DescribeUsersRequest implements Serializable {
    private int pageNumber;

    private int pageSize;
}
