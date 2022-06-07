package com.example.template.service;

public interface RedisService {
    boolean verifyScript(String expectedValue, String newValue);

    void verifySupportCollections();
}
