package com.example.template.mapper;

import com.example.template.domain.Person;
import com.example.template.dto.CreatePersonRequest;
import com.example.template.dto.DescribePersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toDomain(CreatePersonRequest request);

    DescribePersonResponse toDTO(Person person);
}
