package com.todo.todoapi.service;

import org.springframework.stereotype.Service;

import com.todo.todoapi.model.User;
import com.todo.todoapi.repository.UserRepository;

@Service
public class UserService {
  
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser() {
    User user = new User();
    user.setName("John");
    user.setLastName("Doe");
    user.setUsername("username");
    user.setPassword("password");
    user.setActive(true);
    return userRepository.save(user);
  }

}