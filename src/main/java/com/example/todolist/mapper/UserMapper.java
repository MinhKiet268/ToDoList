package com.example.todolist.mapper;


import com.example.todolist.dto.UserDTO;
import com.example.todolist.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDTO userDTO);


    List<UserEntity> toEntity(List<UserDTO> users);

    UserDTO toDto(UserEntity user);
}
