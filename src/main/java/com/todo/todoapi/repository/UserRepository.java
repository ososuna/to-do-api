package com.todo.todoapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.todoapi.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}