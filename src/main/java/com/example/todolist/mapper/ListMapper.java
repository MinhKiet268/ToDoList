package com.example.todolist.mapper;

import com.example.todolist.dtoResponse.ListDTO;
import com.example.todolist.entity.ListEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ListMapper {
    ListDTO toDto(ListEntity list);
    ListEntity toEntity(ListDTO list);
    Set<ListDTO> toDtos(Set<ListEntity> lists);
}
