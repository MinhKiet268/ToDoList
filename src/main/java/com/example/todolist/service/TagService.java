package com.example.todolist.service;

import com.example.todolist.config.ContextUtils;
import com.example.todolist.dtoResponse.TagDTO;
import com.example.todolist.entity.TagEntity;
import com.example.todolist.exception.DataBaseExecutionException;
import com.example.todolist.mapper.TagMapper;
import com.example.todolist.repository.TagRepository;
import com.example.todolist.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    private final TagMapper tagMapper;

    public Set<TagDTO> findAllByUserId(long id) {
        Set<TagEntity> tags = tagRepository.findAllByUserId(id).orElse(null);
        return tagMapper.toDtos(tags);
    }

    public Set<TagEntity> findAllByIdInAndUserId(Set<Long> ids, long userId) {
        try {
            Set<TagEntity> tags = tagRepository.findAllByIdInAndUserId(ids, userId).orElse(null);
            if(tags == null || tags.isEmpty()){
                throw new DataBaseExecutionException("Tags not exist");
            }
            return tags;
        } catch (PersistenceException ex) {
            throw new DataBaseExecutionException("Tags not exist");
        }
    }

    public TagDTO save(TagDTO tagDTO) {
        try {
            TagEntity tagEntity = tagMapper.toEntity(tagDTO);
            tagEntity.setUser(userRepository.getReferenceById(ContextUtils.getUserId()));
            TagEntity savedTag = tagRepository.save(tagEntity);
            return tagMapper.toDto(savedTag);
        } catch (PersistenceException ex) {
            throw new DataBaseExecutionException("Tag could not be created");
        }
    }

    public void deleteById(long id) {
        tagRepository.deleteByIdAndUserId(id, ContextUtils.getUserId());
    }

}
