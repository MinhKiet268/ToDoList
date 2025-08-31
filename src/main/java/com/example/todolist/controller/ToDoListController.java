package com.example.todolist.controller;

import com.example.todolist.entity.Task;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/todolist")
public class ToDoListController {
    private TaskService taskService;

    public ToDoListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public String Add(@RequestBody Task task) {
        try {
            taskService.getTaskById(taskService.addTask(task));
        } catch (Exception e) {
            return "Cannot add task: " + e.getMessage();
        }
        return "Task added";
    }

    @GetMapping("/get")
    public List<Task> getAll() {
        return taskService.getAll();
    }

}
