package com.todo.todoapi.model.enums;

public enum Role {

  USER("User"),
  ADMIN("Admin");

  private final String text;

  /**
   * @param text
   */
  Role(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}
