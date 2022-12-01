package com.todo.todoapi.util;

import org.springframework.stereotype.Component;

import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.model.dto.TodoDto;

@Component
public class ToDoUtil {

  public TodoDto convertToDotoDto(ToDo todo) {
    TodoDto todoDto = new TodoDto();
    todoDto.setId(todo.getId());
    todoDto.setDate(todo.getDate());
    todoDto.setTitle(todo.getTitle());
    todoDto.setDescription(todo.getDescription());
    todoDto.setStatus(todo.getStatus());
    return todoDto;
  }

}
