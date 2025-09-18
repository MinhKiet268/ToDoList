package com.example.todolist.mapper;


import com.example.todolist.dtoRequest.UserCreationRequestDTO;
import com.example.todolist.dtoResponse.UserResponse;
import com.example.todolist.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserCreationRequestDTO userCreationRequestDTO);

    UserEntity toEntity(UserResponse userResponse);

    List<UserEntity> toEntity(List<UserCreationRequestDTO> users);

    UserCreationRequestDTO toDto(UserEntity user);

    UserResponse toResponseDto(UserEntity user);
}
