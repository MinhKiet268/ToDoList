package com.example.todolist.controller;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("api/todolist")
public class ToDoListController {
    private TaskService taskService;

    public ToDoListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public String Add(@RequestBody TaskDTO task) {
            Optional<TaskDTO> returnTask = taskService.addTask(task);
            if (returnTask.isEmpty()) {
                return "Cannot add task";
            }
            return "Task added successfully";
    }

    @GetMapping("/get")
    public List<TaskDTO> getAll() {
        return taskService.getAll();
    }

}
