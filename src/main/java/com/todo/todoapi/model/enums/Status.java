package com.todo.todoapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
  PENDING ("Pending"),
  COMPLETED ("Completed");

  private String type;

}