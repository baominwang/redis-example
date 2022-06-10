package com.example.template.repository;

import com.example.template.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    Optional<Person> findByFirstname(String firstname);
}
