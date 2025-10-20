package com.example.todolist.dtoResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private long id;
    private String name;
    private String description;
    private String status;
    private Date dueDate;
    private long listId;
    private Set<Long> tagIds;

}
