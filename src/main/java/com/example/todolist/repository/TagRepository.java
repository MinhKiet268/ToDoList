package com.example.todolist.repository;

import com.example.todolist.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    public Optional<Set<TagEntity>> findAllByIdInAndUserId(Set<Long> id, long userId);

    public Optional<Set<TagEntity>> findAllByUserId(long userId);

    public void deleteByIdAndUserId(long id, long userId);
}
