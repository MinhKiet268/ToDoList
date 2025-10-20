package com.example.todolist.controller;

import com.example.todolist.config.ContextUtils;
import com.example.todolist.dtoResponse.ListDTO;
import com.example.todolist.dtoResponse.TagDTO;
import com.example.todolist.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/app/tag")
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
@RestController
public class TagController {

    private final TagService tagService;

    @PostMapping({"/create", "/update"})
    public ResponseEntity<TagDTO> save(@RequestBody TagDTO task) {
        return new ResponseEntity<>(tagService.save(task), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Set<TagDTO>> get() {
        return new ResponseEntity<>(tagService.findAllByUserId(ContextUtils.getUserId()), HttpStatus.OK);
    }

}
