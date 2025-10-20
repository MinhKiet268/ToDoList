package com.example.todolist.service;

import com.example.todolist.config.ContextUtils;
import com.example.todolist.dtoResponse.TaskDTO;
import com.example.todolist.entity.ListEntity;
import com.example.todolist.entity.TagEntity;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.exception.DataBaseExecutionException;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.repository.ListRepository;
import com.example.todolist.repository.TagRepository;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class TaskService{

    private final TaskRepository taskRepository;

    private final CustomUserService userService;

    private final ListService listService;

    private final TagService tagService;

    @Autowired
    private TaskMapper taskMapper;

        @Transactional
        public TaskDTO save(TaskDTO task) {

            long userId = ContextUtils.getUserId();
            TaskEntity taskEntity = taskMapper.toEntity(task);
            taskEntity.setUser(userService.getReferenceById(userId));

            if(task.getTagIds() != null && !task.getTagIds().isEmpty()){
                Set<TagEntity> tags = tagService.findAllByIdInAndUserId(task.getTagIds(), userId);
                if(tags == null || tags.isEmpty()){
                    throw new DataBaseExecutionException("Tags not found or belong to another user");
                }
                taskEntity.setTags(tags);
            }

            if(task.getListId()!=0){
                ListEntity list = listService.findByIdAndUserId(task.getListId(), userId);
                taskEntity.setList(list);
            }

            TaskEntity savedTask = taskRepository.save(taskEntity);
            return taskMapper.toDtoTaskId(savedTask);
        }



        public List<TaskDTO> getTaskByUserId() {
            List<TaskEntity> savedTask = taskRepository.findByUserId(ContextUtils.getUserId())
                    .orElseThrow(() -> new DataBaseExecutionException("Get tasks failed"));
            return taskMapper.toDtoTaskIds(savedTask);
        }

        public TaskDTO getTaskById(long id) {
            TaskEntity savedTask = taskRepository.findById(id).orElse(null);
            return taskMapper.toDtoTaskId(savedTask);
        }

        
        public List<TaskDTO> getAll() {
            List<TaskEntity> tasks = taskRepository.findAll();
            List<TaskDTO> dtos = taskMapper.toDtoTaskIds(tasks);
            return dtos;
        }

        @Transactional
        public void deleteTaskById(long id) {
            taskRepository.deleteById(id);
        }

        @Transactional
        public void deleteAllTasks() {
            taskRepository.deleteAll();
        }



    }
