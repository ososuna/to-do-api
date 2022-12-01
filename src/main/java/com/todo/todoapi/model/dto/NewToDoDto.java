package com.todo.todoapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewToDoDto {
  private String title;
  private String description;  
}
