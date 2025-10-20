package com.example.todolist.repository;

import com.example.todolist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<List<TaskEntity>> findByUserId(long userId);

    @Modifying
    @Query (value = "INSERT INTO task (user_id,description,name,priority,status) VALUES (:userId,:description,:name,:priority,:status)", nativeQuery = true)
    public Optional<TaskEntity> add (@Param("userId") long userId,
                                     @Param("description") String description,
                                     @Param("name") String name,
                                     @Param("priority") String priority,
                                     @Param("status") String status,
                                     @Param("tags") ArrayList<Long> tags,
                                     @Param("lists") long lists
    );


}
