package com.example.template.service;

public interface RedisService {
    void testValueOperations();

    void testHashOperations();

    boolean verifyScript(String expectedValue, String newValue);

    void verifySupportCollections();
}
