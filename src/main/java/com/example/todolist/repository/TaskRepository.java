package com.example.todolist.repository;

import com.example.todolist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByUserId(long userId);

    @Modifying
    @Query (value = "INSERT INTO task (user_id,description,name,priority,status) VALUES (:userId,:description,:name,:priority,:status)", nativeQuery = true)
    void add (@Param("userId") long userId
            ,@Param("description") String description
            ,@Param("name") String name
            ,@Param("priority") String priority
            ,@Param("status") String status);
}
