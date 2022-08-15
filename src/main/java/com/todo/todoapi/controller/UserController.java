package com.todo.todoapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
}
