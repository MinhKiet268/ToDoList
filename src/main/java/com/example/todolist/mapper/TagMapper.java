package com.example.todolist.mapper;

import com.example.todolist.dtoResponse.TagDTO;
import com.example.todolist.entity.TagEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper (componentModel = "spring")
public interface TagMapper {
    Set<TagDTO> toDtos(Set<TagEntity> tagIds);
    TagEntity toEntity(TagDTO dto);

    TagDTO toDto(TagEntity dto);
}
