package com.example.template.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.template.domain.SortUtils;
import com.example.template.domain.User;
import com.example.template.dto.CreateUserRequest;
import com.example.template.dto.DescribeUsersRequest;
import com.example.template.mapper.UserMapper;
import com.example.template.repository.UserRepository;
import com.example.template.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String create(CreateUserRequest request) {
        // build the user object
        User user = UserMapper.INSTANCE.toDomain(request);
        String userId = IdUtil.getSnowflakeNextIdStr();
        user.setUserId(userId);

        // store the user record into the Redis
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String key = "user:" + userId;
        String value = request.getFirstName() + " " + request.getLastName();
        ops.set(key, value);
        String storedValue = (String)ops.get(key);
        log.info("The value for key: {} is {}", key, storedValue);

        // record the operation in the log
        Map<String, String> content = new HashMap<>();
        content.put("timestamp", new Date().toString());
        content.put("userId", userId);
        StringRecord record = StreamRecords.string(content).withStreamKey("users:audit");
        RecordId recordId = redisTemplate.opsForStream().add(record);

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
