package com.example.template.control;

import com.example.template.CreatePersonResponse;
import com.example.template.domain.Person;
import com.example.template.dto.CreatePersonRequest;
import com.example.template.dto.DescribePersonRequest;
import com.example.template.dto.DescribePersonResponse;
import com.example.template.mapper.PersonMapper;
import com.example.template.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "persons")
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public CreatePersonResponse create(@RequestBody @Valid CreatePersonRequest request) {
        String personId = personService.create(request);

        CreatePersonResponse response = new CreatePersonResponse();
        response.setId(personId);

        return response;
    }

    @GetMapping
    public DescribePersonResponse describe(@RequestBody @Valid DescribePersonRequest request) {
        Optional<Person> optional = personService.describe(request);
        if (!optional.isPresent()) {
            log.error("Person: {} does not exist", request.getFirstname());
            throw new RuntimeException();
        }

        return PersonMapper.INSTANCE.toDTO(optional.get());
    }
}
