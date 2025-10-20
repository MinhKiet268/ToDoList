package com.example.todolist.repository;

import com.example.todolist.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ListRepository extends JpaRepository<ListEntity, Long> {

    //public Optional<ListEntity> findAllByIdInAndUserId(long id, long userId);

    public Optional<ListEntity> findByIdAndUserId(long id, long userId);

    public Optional<Set<ListEntity>> findAllByUserId(long userId);

    public void deleteByIdAndUserId(long id, long userId);

}
