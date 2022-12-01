package com.todo.todoapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.todoapi.model.ToDo;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
  List<ToDo> findAll();

  List<ToDo> findAllByActiveTrue();
}