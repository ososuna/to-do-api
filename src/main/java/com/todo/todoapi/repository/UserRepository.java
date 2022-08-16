package com.todo.todoapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.todoapi.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findById(String id);
  Optional<User> findByUsername(String username);

}