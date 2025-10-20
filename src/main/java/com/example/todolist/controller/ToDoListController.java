package com.example.todolist.controller;

import com.example.todolist.config.JwtUtil;
import com.example.todolist.dtoResponse.TaskDTO;
import com.example.todolist.exception.InvalidInputException;
import com.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("api/app/task")
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')") // you can use hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') if your roles are prefixed with 'ROLE_'
public class ToDoListController {
    private TaskService taskService;

    private JwtUtil jwtUtil;

    public ToDoListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> Add(@RequestBody TaskDTO task) {

        TaskDTO returnTask = taskService.save(task);

        return new ResponseEntity<>(returnTask, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<TaskDTO> getAll() {
        return taskService.getTaskByUserId();
    }

    @GetMapping("/get/{id}")
    public TaskDTO getById(@PathVariable long taskId) {
        TaskDTO returnTask = taskService.getTaskById(taskId);
        return returnTask;
    }

}
