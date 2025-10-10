package com.example.todolist.mapper;

import com.example.todolist.dtoResponse.TaskDTO;
import com.example.todolist.entity.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    //@Mapping(target = "id", ignore = true)
    TaskEntity toEntityNoId(TaskDTO task);

    TaskEntity toEntity(TaskDTO task);

    TaskDTO toDto(TaskEntity task);

    List<TaskDTO> toDtos(List<TaskEntity> tasks);
}
