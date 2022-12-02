package com.todo.todoapi.model.dto;

import com.todo.todoapi.model.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateToDoDto {
  private String title;
  private String description;
  private Status status;
}
