package com.example.todolist.repository;

import com.example.todolist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByUserId(long userId);
}
