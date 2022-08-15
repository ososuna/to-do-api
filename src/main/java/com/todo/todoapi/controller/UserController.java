package com.todo.todoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoapi.model.User;
import com.todo.todoapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> createUser() {
    return new ResponseEntity<>(userService.createUser(), HttpStatus.CREATED);
  }

}
