package com.example.template.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Data
public class DescribeUsersResponse implements Serializable {
    private long totalCount;
    private int pageNumber;
    private int pageSize;
    private List<User> users;

    @Data
    public static class User {
        private String userId;

        private String firstName;

        private String lastName;
    }
}
