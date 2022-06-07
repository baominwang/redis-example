package com.example.template.control;

import com.example.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "redis")
@Slf4j
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping(value = "/actions/verify_script")
    public void verifyScript() {
        boolean result = redisService.verifyScript("failed", "ok");
        log.info("Result is {}", result);
    }

    @PostMapping(value = "/actions/verify_support_collections")
    public void verifySupportCollections() {
        redisService.verifySupportCollections();
    }
}
