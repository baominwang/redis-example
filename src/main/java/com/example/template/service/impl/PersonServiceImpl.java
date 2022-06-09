package com.example.template.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.template.domain.Person;
import com.example.template.dto.CreatePersonRequest;
import com.example.template.mapper.PersonMapper;
import com.example.template.repository.PersonRepository;
import com.example.template.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public String create(CreatePersonRequest request) {
        Person person = PersonMapper.INSTANCE.toDomain(request);
        String id = IdUtil.getSnowflakeNextIdStr();

        person.setId(id);
        personRepository.save(person);
        log.info("Person: {} is persisted", id);

        return id;
    }
}
