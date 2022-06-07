package com.example.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RedisTestImpl {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() throws Exception {
        String key = "multiThreadTest";

        redisTemplate.delete(key);

        Runnable runable = () -> redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            @SuppressWarnings({"unchecked"})
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                List<Object> result = null;
                do {
                    operations.watch(key);

                    int count = 0;
                    String value = (String) operations.opsForValue().get(key);
                    if (StringUtils.hasLength(value)) {
                        count = Integer.parseInt(value);
                    }
                    count = count + 1;

                    try {
                        operations.multi();
                        operations.opsForValue().set(key, String.valueOf(count));
                        result = operations.exec();
                    } catch (Exception ex) {
                        log.error("Caught exception", ex);
                    }
                } while (result.size() == 0);

                return result;
            }
        });
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runable, "thread-" + (i + 1));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        String value = (String) redisTemplate.opsForValue().get(key);
        log.info("The result is {}", value);
    }


}
