package com.example.template.service;

import com.example.template.domain.Person;
import com.example.template.dto.CreatePersonRequest;
import com.example.template.dto.DescribePersonRequest;

import java.util.Optional;

public interface PersonService {
    String create(CreatePersonRequest request);

    Optional<Person> describe(DescribePersonRequest request);
}
