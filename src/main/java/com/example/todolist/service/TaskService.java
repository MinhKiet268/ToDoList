package com.example.todolist.service;

import com.example.todolist.repository.TaskRepository;
import com.example.todolist.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TaskService {
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskService() {
    }

    @Transactional
    public long addTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    @Transactional
    public long updateTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
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
