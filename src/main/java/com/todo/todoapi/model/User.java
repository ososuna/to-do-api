package com.todo.todoapi.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("users")
public class User {

  @Id
  private String id;

  private String username;
  private String name;
  private String lastName;
  private String password;
  private boolean active;
  private Set<ToDo> todos;

} 