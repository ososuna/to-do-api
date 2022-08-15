package com.todo.todoapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.todoapi.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}