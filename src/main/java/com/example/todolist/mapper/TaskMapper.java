package com.example.todolist.mapper;

import com.example.todolist.dtoResponse.TaskDTO;
import com.example.todolist.entity.ListEntity;
import com.example.todolist.entity.TagEntity;
import com.example.todolist.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    //@Mapping(target = "id", ignore = true)
    TaskEntity toEntityNoId(TaskDTO task);

    TaskEntity toEntity(TaskDTO task);

//
//    TaskDTO toDto(TaskEntity task);
//
//    List<TaskDTO> toDtos(List<TaskEntity> tasks);



    @Mapping(source = "tags", target = "tagIds", qualifiedByName = "tasksToIds")
    @Mapping(source = "list", target = "listId", qualifiedByName = "listToId")
    TaskDTO toDtoTaskId(TaskEntity task);

    @Mapping(source = "tags", target = "tagIds", qualifiedByName = "tasksToIds")
    @Mapping(source = "list", target = "listId", qualifiedByName = "listToId")
    Set<TaskDTO> toDtoTaskIds(Set<TaskEntity> task);

    @Mapping(source = "tags", target = "tagIds", qualifiedByName = "tasksToIds")
    @Mapping(source = "list", target = "listId", qualifiedByName = "listToId")
    List<TaskDTO> toDtoTaskIds(List<TaskEntity> task);



    @Named("tasksToIds")
    static Set<Long> toIds(Set<TagEntity> tag) {
        if(tag == null) {
            return null;
        } else {
            return tag.stream().map(TagEntity::getId).collect(Collectors.toSet());
        }
    }

    @Named("listToId")
    static long toId(ListEntity list) {
        if(list == null) {
            return 0;
        } else {
            return list.getId();
        }
    }

}
