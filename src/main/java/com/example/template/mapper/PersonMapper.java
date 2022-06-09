package com.example.template.mapper;

import com.example.template.domain.Person;
import com.example.template.dto.CreatePersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toDomain(CreatePersonRequest request);
}
