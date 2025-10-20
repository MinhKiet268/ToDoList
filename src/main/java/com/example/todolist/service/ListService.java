package com.example.todolist.service;


import com.example.todolist.config.ContextUtils;
import com.example.todolist.dtoResponse.ListDTO;
import com.example.todolist.dtoResponse.TagDTO;
import com.example.todolist.entity.ListEntity;
import com.example.todolist.entity.TagEntity;
import com.example.todolist.exception.DataBaseExecutionException;
import com.example.todolist.mapper.ListMapper;
import com.example.todolist.repository.ListRepository;
import com.example.todolist.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Set;

//@Slf4j just use Logger
@RequiredArgsConstructor
@Service
public class ListService {
    private final ListRepository listRepository;

    private final CustomUserService userService;

    private final ListMapper listMapper;



    public Set<ListDTO> findAllByUserId(long id) {
        Set<ListEntity> list = listRepository.findAllByUserId(id).orElse(null);
        return listMapper.toDtos(list);
    }

    public ListEntity findByIdAndUserId(long id, long userId) {
        ListEntity listEntity = listRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new DataBaseExecutionException("List not found with id: " + id + " and userId: " + userId));
        return listEntity;
    }

    public ListDTO save(ListDTO listDTO) {
        try {
            ListEntity listEntity = listMapper.toEntity(listDTO);
            listEntity.setUser(userService.getReferenceById(ContextUtils.getUserId()));
            ListEntity savedTag = listRepository.save(listEntity);
            return listMapper.toDto(savedTag);
        } catch (PersistenceException ex) {
            throw new DataBaseExecutionException("Tag could not be created");
        }
    }

    public void deleteById(long id) {
        listRepository.deleteByIdAndUserId(id, ContextUtils.getUserId());
    }
}
