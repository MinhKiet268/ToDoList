package com.example.todolist.service;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;


    @Transactional
    public Optional<TaskDTO> addTask(TaskDTO task) {
        TaskEntity savedTask = taskRepository.save(taskMapper.toEntityNoId(task));
        return Optional.of(taskMapper.toDto(savedTask));
    }

    @Transactional
    public Optional<TaskDTO> updateTask(com.example.todolist.dto.TaskDTO task) {
        TaskEntity savedTask = taskRepository.save(taskMapper.toEntity(task));
        return Optional.of(taskMapper.toDto(savedTask));
    }

    public Optional<TaskDTO> getTaskById(long id) {
        TaskEntity savedTask = taskRepository.findById(id).orElse(null);
        return Optional.of(taskMapper.toDto(savedTask));
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

}
