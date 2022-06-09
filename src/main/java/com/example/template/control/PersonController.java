package com.example.template.control;

import com.example.template.CreatePersonResponse;
import com.example.template.dto.CreatePersonRequest;
import com.example.template.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "persons")
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
}
