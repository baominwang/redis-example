package com.example.template.service.impl;

import com.example.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
