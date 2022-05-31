package com.example.template.control;

import com.example.template.domain.User;
import com.example.template.dto.CreateUserRequest;
import com.example.template.dto.CreateUserResponse;
import com.example.template.dto.DescribeUsersRequest;
import com.example.template.dto.DescribeUsersResponse;
import com.example.template.mapper.UserMapper;
import com.example.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public CreateUserResponse create(@RequestBody @Valid CreateUserRequest request) {
        String userId = userService.create(request);

        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(userId);

        return response;
    }

    @GetMapping
    public DescribeUsersResponse list(@RequestBody DescribeUsersRequest request) {
        Page<User> users = userService.list(request);

        DescribeUsersResponse response = new DescribeUsersResponse();
        response.setTotalCount(users.getTotalElements());
        response.setPageNumber(request.getPageNumber());
        response.setPageSize(request.getPageSize());
        response.setUsers(users.getContent().stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));

        return response;
    }
}
