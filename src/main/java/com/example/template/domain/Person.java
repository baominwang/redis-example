package com.example.template.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.*;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("person")
@Getter
@Setter
public class Person {
    @Id
    String id;

    @Indexed
    String firstname;

    String lastname;
}
