package com.example.template.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.*;

@RedisHash("person")
@Getter
@Setter
public class Person {
    @Id
    String id;

    String firstname;

    String lastname;
}
