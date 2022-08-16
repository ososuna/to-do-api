package com.todo.todoapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.todo.todoapi.exception.BadRequestException;
import com.todo.todoapi.exception.NotFoundException;
import com.todo.todoapi.exception.TodoException;
import com.todo.todoapi.model.ToDo;
import com.todo.todoapi.service.TodoService;

@WebMvcTest(TodoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TodoControllerUnitTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService todoService;

  TodoException notFoundException = new NotFoundException("error");
  TodoException badRequestException = new BadRequestException("error");

  @Test
  public void testGetAllActiveTodos() throws Exception {
    List<ToDo> todos = new ArrayList<>();
    when(todoService.getTodo()).thenReturn(todos);
    this.mockMvc.perform(get("/todo/api/v2/todo"))
      .andExpect(status().isOk())
      .andExpect(result -> result.getClass().equals(List.class));
  }

}
