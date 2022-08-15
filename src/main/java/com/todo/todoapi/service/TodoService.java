package com.todo.todoapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.todo.todoapi.exception.NotFoundException;
import com.todo.todoapi.exception.TodoException;
import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.model.dto.TodoDto;
import com.todo.todoapi.repository.ToDoRepository;
import com.todo.todoapi.repository.UserRepository;

@Service
public class TodoService {
  
  private ToDoRepository toDoRepository;
  private UserRepository userRepository;

  public TodoService( ToDoRepository toDoRepository, 
                      UserRepository userRepository) {
    
    this.toDoRepository = toDoRepository;
    this.userRepository = userRepository;

  }

  public List<ToDo> getTodo() throws TodoException {
    var todos = toDoRepository.findByActiveTrue();
    if (todos.isEmpty()) {
      throw new NotFoundException("No todos found");
    }
    return todos;
  }

  public ToDo createTodo(String userId, ToDo todo) throws TodoException {
    var user = userRepository.findById(userId).get();
    if (user != null) {
      todo.setUser(user);
      return toDoRepository.save(todo);
    }
    throw new NotFoundException("User not found");
  }

  public ToDo updateTodo(TodoDto todoDto) {
    ToDo todo = new ToDo();
    BeanUtils.copyProperties(todoDto, todo);
    return toDoRepository.save(todo);
  }

}
