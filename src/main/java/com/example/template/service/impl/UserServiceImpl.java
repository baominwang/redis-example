package com.example.template.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.template.domain.SortUtils;
import com.example.template.domain.User;
import com.example.template.dto.CreateUserRequest;
import com.example.template.dto.DescribeUsersRequest;
import com.example.template.mapper.UserMapper;
import com.example.template.repository.UserRepository;
import com.example.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(CreateUserRequest request) {
        // build the user object
        User user = UserMapper.INSTANCE.toDomain(request);
        String userId = IdUtil.getSnowflakeNextIdStr();
        user.setUserId(userId);

        // persist the user
        userRepository.save(user);

        return userId;
    }

    @Override
    public Page<User> list(DescribeUsersRequest request) {
        int pageNumber = request.getPageNumber();
        int pageSize = request.getPageSize();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, SortUtils.getDefaultSort());

        return userRepository.findAll(pageRequest);
    }
}
