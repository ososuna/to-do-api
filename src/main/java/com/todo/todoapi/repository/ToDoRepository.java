package com.todo.todoapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.todoapi.model.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, String> {

}