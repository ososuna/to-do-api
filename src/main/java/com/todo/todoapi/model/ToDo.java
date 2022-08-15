package com.todo.todoapi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.todo.todoapi.model.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("toDos")
public class ToDo {

  @Id
  private String id;

  private String title;
  private String description;
  private LocalDate date;
  private User user;
  private Status status;
  private boolean active;


}
