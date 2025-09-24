package com.example.todolist.service;

import com.example.todolist.dtoResponse.TaskDTO;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;


    @Transactional
    public Optional<TaskDTO> addTask(TaskDTO task) {
        TaskEntity savedTask = taskRepository.save(taskMapper.toEntityNoId(task));
        if(savedTask != null) {
            return Optional.of(taskMapper.toDto(savedTask));
        }else {
            throw new ResourceNotFoundException("Task could not be added");
        }
    }

    @Transactional
    public Optional<TaskDTO> updateTask(TaskDTO task) {
        TaskEntity savedTask = taskRepository.save(taskMapper.toEntity(task));
        return Optional.of(taskMapper.toDto(savedTask));
    }

    public Optional<TaskDTO> getTaskById(long id) {
        TaskEntity savedTask = taskRepository.findById(id).orElse(null);
        return Optional.of(taskMapper.toDto(savedTask));
    }

    public List<TaskDTO> getTaskByUserId() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TaskEntity> savedTask = taskRepository.findByUserId(user.getId());
        return taskMapper.toDtos(savedTask);
    }

    public List<TaskDTO> getAll() {
        List<TaskEntity> tasks = taskRepository.findAll();
        List<TaskDTO> dtos = taskMapper.toDtos(tasks);
        return dtos;
    }

    @Transactional
    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public UserEntity getUserIdFromContext() {
        return (UserEntity) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
