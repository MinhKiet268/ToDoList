package com.example.todolist.controller;

import com.example.todolist.config.ContextUtils;
import com.example.todolist.dtoResponse.ListDTO;
import com.example.todolist.service.ListService;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/app/list")
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
@RestController
public class ListController {

    private final ListService listService;

    @PostMapping({"/create", "/update"})
    public ResponseEntity<ListDTO> save(@RequestBody ListDTO task) {
        return new ResponseEntity<>(listService.save(task), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Set<ListDTO>> get() {
        return new ResponseEntity<>(listService.findAllByUserId(ContextUtils.getUserId()), HttpStatus.OK);
    }


}
