package com.hazem.todo_app.controllers;

import com.hazem.todo_app.dto.TodoDTO;
import com.hazem.todo_app.entities.Todo;
import com.hazem.todo_app.services.JWTService;
import com.hazem.todo_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Map;

@RestController
public class TodoController {
  private JWTService jwtService;
  private UserService userService;

  @Autowired
  public TodoController(JWTService jwtService, UserService userService) {
    this.jwtService = jwtService;
    this.userService = userService;
  }

  private String getTokenFromHeader(String header) {
    return header.split(" ")[1];
  }

  @GetMapping("/todos")
  ResponseEntity<?> getTodosForUser(
      @RequestHeader("Authorization") String header,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int limit) {
    String email = jwtService.getEmailFromToken(getTokenFromHeader(header));
    Pageable pageable = PageRequest.of(page, limit);
    Page<Todo> todos = userService.getTodosForUser(email, pageable);

    return ResponseEntity.ok(
        Map.of(
            "data", todos.getContent(),
            "page", todos.getNumber(),
            "limit", todos.getSize(),
            "total", todos.getTotalElements()
        ));
  }

  @GetMapping("/todos/{todoId}")
  ResponseEntity<Todo> getSingleTodoForUser(
      @RequestHeader("Authorization") String header,
      @PathVariable Long todoId) throws AccessDeniedException {
    String email = jwtService.getEmailFromToken(getTokenFromHeader(header));
    return ResponseEntity.ok(userService.getTodoByIdForUser(todoId, email));
  }


  @PostMapping("/todos")
  ResponseEntity<Todo> addTodoForUser(
      @RequestHeader("Authorization") String header,
      @RequestBody TodoDTO todoDTO
  ) {
    String email = jwtService.getEmailFromToken(getTokenFromHeader(header));
    return ResponseEntity
        .status(HttpStatus.CREATED.value())
        .body(userService.addTodoForUser(todoDTO, email));
  }

  @PutMapping("/todos/{todoId}")
  ResponseEntity<Todo> updateTodoForUser(
      @RequestHeader("Authorization") String header,
      @PathVariable Long todoId,
      @RequestBody TodoDTO todoDTO
  ) throws AccessDeniedException {
    String email = jwtService.getEmailFromToken(getTokenFromHeader(header));
    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(userService.updateTodoForUser(todoId, todoDTO, email));
  }

  @DeleteMapping("/todos/{todoId}")
  ResponseEntity<Void> deleteTodoForUser(
      @RequestHeader("Authorization") String header,
      @PathVariable Long todoId
  ) throws AccessDeniedException {
    String email = jwtService.getEmailFromToken(getTokenFromHeader(header));
    userService.deleteTodoForUser(todoId, email);
    return ResponseEntity.noContent().build();
  }

}
