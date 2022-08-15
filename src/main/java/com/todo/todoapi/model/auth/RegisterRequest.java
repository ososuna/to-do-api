package com.todo.todoapi.model.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
  
  private String name;
  private String lastName;
  private String username;
  private String password;

}