package com.example.todolist.config;

import com.example.todolist.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtils {
    public static long getUserId() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
