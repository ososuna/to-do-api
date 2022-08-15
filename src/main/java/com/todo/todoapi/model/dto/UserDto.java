package com.todo.todoapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

  private String id;
  private String username;
  private String name;
  private String lastName;

}
