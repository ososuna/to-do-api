package com.todo.todoapi.model.dto;

import java.time.LocalDate;

import com.todo.todoapi.model.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
  private String id;
  private String title;
  private String description;
  private LocalDate date;
  private Status status;
  private boolean active;
}