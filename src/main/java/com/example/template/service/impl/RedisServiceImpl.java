package com.example.template.service.impl;

import com.example.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Boolean> checkAndSetScript;

    @Override
    public boolean verifyScript(String expectedValue, String newValue) {
        return Boolean.TRUE.equals(stringRedisTemplate.execute(checkAndSetScript, Collections.singletonList("key"), expectedValue, newValue));
    }
}
