package com.todo.todoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo.todoapi.exception.NotFoundException;
import com.todo.todoapi.exception.TodoException;
import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.model.User;
import com.todo.todoapi.model.dto.TodoDto;
import com.todo.todoapi.model.enums.Status;
import com.todo.todoapi.repository.ToDoRepository;
import com.todo.todoapi.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class TodoServiceUnitTest {
  
  @InjectMocks
  private TodoService todoService;

  @Mock
  private ToDoRepository toDoRepository;

  @Mock
  private UserRepository userRepository;

  public TodoDto getTodoDto() {
    var todoDto = new TodoDto();
    todoDto.setId("12345678");
    todoDto.setTitle("title");
    todoDto.setDescription("description");
    todoDto.setDate(LocalDate.now());
    todoDto.setStatus(Status.PENDING);
    todoDto.setActive(true);
    return todoDto;
  }
  
  @Test
  void testGetTodoSuccess() throws TodoException {
    var todos = new ArrayList<ToDo>();
    todos.add(new ToDo());
    when(toDoRepository.findByActiveTrue()).thenReturn(todos);
    assertEquals(todos, todoService.getTodo());
  }

  @Test
  void testGetTodoNotFound() throws TodoException {
    var todos = new ArrayList<ToDo>();
    when(toDoRepository.findByActiveTrue()).thenReturn(todos);
    assertThrows(NotFoundException.class, () -> todoService.getTodo());
  }

  @Test
  void testCreateTodoSuccess() throws TodoException {
    Optional<User> user = Optional.of(new User());
    var userId = "123";
    var todo = new ToDo();
    todo.setUser(user.get());

    when(userRepository.findById(userId)).thenReturn(user);
    when(toDoRepository.save(any())).thenReturn(todo);
    assertEquals(todo, todoService.createTodo(userId, new ToDo()));
  }

  @Test
  void testCreateTodoUserNotFound() throws TodoException {
    var userId = "123";
    Optional<User> user = Optional.empty();
    var todo = new ToDo();
    when(userRepository.findById(userId)).thenReturn(user);
    assertThrows(NotFoundException.class, () -> todoService.createTodo(userId, todo));
  }

  @Test
  void testUpdateTodoSuccess() throws TodoException {
    var todo = new ToDo();
    var todoDto = getTodoDto();
    when(toDoRepository.findById(todoDto.getId())).thenReturn(Optional.of(todo));
    when(toDoRepository.save(any())).thenReturn(todo);
    assertEquals(todo, todoService.updateTodo(todoDto));
  }

  @Test
  void testUpdateTodoNotFound() throws TodoException {
    var todoDto = getTodoDto();
    when(toDoRepository.findById(todoDto.getId())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> todoService.updateTodo(todoDto));
  }

}
