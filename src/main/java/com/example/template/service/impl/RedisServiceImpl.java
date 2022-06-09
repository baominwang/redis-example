package com.example.template.service.impl;

import com.example.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    @Qualifier("normalRedisTemplate")
    private RedisTemplate<String, Object> normalRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Boolean> checkAndSetScript;

    @Override
    public void testValueOperations() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("operations:value:key1", "Hello World");
        String result = operations.get("operations:value:key1");
        log.info("operations:value:key1 in redis is {}", result);

        operations.set("operations:value:key1", "Good", 6);
        result = operations.get("operations:value:key1");
        log.info("operations:value:key1 in redis is {}", result);
    }

    @Override
    public void testHashOperations() {
        HashOperations<String, String, Object> operations = normalRedisTemplate.opsForHash();
        operations.put("operations:hash:key1", "name", "Benjamin Wang");
        operations.put("operations:hash:key1", "age", 40);

        Set<String> keys = operations.keys("operations:hash:key1");
        log.info("There are the following keys in the redis:");
        for (String key : keys) {
            log.info(key);
        }

        String name = (String) operations.get("operations:hash:key1", "name");
        log.info("The name is {}", name);

        Integer age = (Integer) operations.get("operations:hash:key1", "age");
        log.info("The age is {}", age);
    }

    @Override
    public boolean verifyScript(String expectedValue, String newValue) {
        return Boolean.TRUE.equals(stringRedisTemplate.execute(checkAndSetScript, Collections.singletonList("key"), expectedValue, newValue));
    }

    @Override
    public void verifySupportCollections() {
        RedisList<String> list = RedisList.create("support:collections", stringRedisTemplate);
        list.add("Baomin Wang");
        list.add("Haitao Cao");
        list.add("Huaiyu Wang");
        List<String> range = list.range(0, 2);
        for (String s : range) {
            log.info(s);
        }
    }


}
