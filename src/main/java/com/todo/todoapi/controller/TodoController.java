package com.todo.todoapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoapi.exception.TodoException;
import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.model.dto.NewToDoDto;
import com.todo.todoapi.model.dto.TodoDto;
import com.todo.todoapi.model.dto.UpdateToDoDto;
import com.todo.todoapi.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

  private TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public ResponseEntity<List<TodoDto>> getAllActiveTodos() throws TodoException {
    return new ResponseEntity<>(todoService.getToDos(), HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<TodoDto>> getAllActiveTodosByUser(@PathVariable String userId) throws TodoException {
    return new ResponseEntity<>(todoService.getToDosByUser(userId), HttpStatus.OK);
  }

  @PostMapping("/{userId}")
  public ResponseEntity<ToDo> createTodo(@PathVariable String userId, @RequestBody NewToDoDto todo) throws TodoException {
    return new ResponseEntity<>(todoService.createTodo(userId, todo), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ToDo> updateTodo(@PathVariable String id, @RequestBody UpdateToDoDto updateToDoDto) throws TodoException {
    return new ResponseEntity<>(todoService.updateTodo(id, updateToDoDto), HttpStatus.CREATED);
  }

}