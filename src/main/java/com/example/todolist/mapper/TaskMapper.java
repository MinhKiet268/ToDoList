package com.example.todolist.mapper;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    TaskEntity toEntityNoId(TaskDTO task);

    TaskEntity toEntity(TaskDTO task);

    List<TaskDTO> toDtoList(List<TaskEntity> tasks);

    Optional<TaskDTO> toDto(TaskEntity task);
}
