package com.hazem.todo_app.exceptions.custom;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String email) {
    super("User with email: " + email + " already exists.");
  }
}
