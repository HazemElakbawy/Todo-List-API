package com.hazem.todo_app.exceptions;

import com.hazem.todo_app.exceptions.custom.TodoNotFoundException;
import com.hazem.todo_app.exceptions.custom.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "User not found",
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "User registration failed",
        ex.getMessage(),
        HttpStatus.CONFLICT.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "Authentication failed",
        "The provided credentials are incorrect. Please check your email and password.",
        HttpStatus.UNAUTHORIZED.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(TodoNotFoundException.class)
  public ResponseEntity<?> handleTodoNotFoundException(TodoNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "Todo not found",
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "Access denied",
        "You do not have permission to perform this action on the requested resource.",
        HttpStatus.FORBIDDEN.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        "Internal server error",
        "An unexpected error occurred. Please try again later or contact support if the problem persists.",
        HttpStatus.INTERNAL_SERVER_ERROR.value()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

