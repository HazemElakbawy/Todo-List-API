package com.hazem.todo_app.exceptions.custom;

public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(Long todoId) {
    super("todo with id: " + todoId + " is not found.");
  }
}
