package com.example.template.mapper;

import com.example.template.domain.User;
import com.example.template.dto.CreateUserRequest;
import com.example.template.dto.DescribeUsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(CreateUserRequest request);
    DescribeUsersResponse.User toDTO(User user);
}
