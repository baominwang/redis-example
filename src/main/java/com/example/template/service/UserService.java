package com.example.template.service;

import com.example.template.domain.User;
import com.example.template.dto.CreateUserRequest;
import com.example.template.dto.DescribeUsersRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    String create(CreateUserRequest request);

    Page<User> list(DescribeUsersRequest request);
}
