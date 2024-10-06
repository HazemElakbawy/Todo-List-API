package com.hazem.todo_app.services;

import com.hazem.todo_app.dto.LoginDTO;
import com.hazem.todo_app.dto.RegisterDTO;
import com.hazem.todo_app.dto.TodoDTO;
import com.hazem.todo_app.entities.Todo;
import com.hazem.todo_app.entities.User;
import com.hazem.todo_app.exceptions.custom.TodoNotFoundException;
import com.hazem.todo_app.exceptions.custom.UserAlreadyExistsException;
import com.hazem.todo_app.repositories.TodoRepository;
import com.hazem.todo_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserRepository userRepository;
  TodoRepository todoRepository;
  JWTService jwtService;
  PasswordEncoder passwordEncoder;

  @Autowired
  public UserService
      (UserRepository userRepository, TodoRepository todoRepository
          , JWTService jwtService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.todoRepository = todoRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
  }


  private User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
  }

  private Todo findTodoById(Long todoId) {
    return todoRepository.findById(todoId)
        .orElseThrow(() -> new TodoNotFoundException(todoId));
  }

  private void verifyUser(User user1, User user2) throws AccessDeniedException {
    if (!user1.getUserId().equals(user2.getUserId())) {
      throw new AccessDeniedException("User does not have permission to access this resource");
    }
  }

  public String registerUser(RegisterDTO registerDTO) {
    if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
      throw new UserAlreadyExistsException(registerDTO.getEmail());
    }

    User user = new User(
        registerDTO.getUsername(),
        registerDTO.getEmail(),
        passwordEncoder.encode(registerDTO.getPassword())
    );

    userRepository.save(user);
    return jwtService.generateToken(user.getEmail());
  }

  public String loginUser(LoginDTO loginDTO) {
    User user = findByEmail(loginDTO.getEmail());
    if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Invalid email or password");
    }
    return jwtService.generateToken(loginDTO.getEmail());
  }

  public Page<Todo> getTodosForUser(String email, Pageable pageable) {
    User user = findByEmail(email);
    return todoRepository.findByUser(user, pageable);
  }

  public Todo getTodoByIdForUser(Long todoId, String email) throws AccessDeniedException {
    User user = findByEmail(email);
    Todo todo = findTodoById(todoId);
    verifyUser(todo.getUser(), user);
    return todo;
  }

  public Todo addTodoForUser(TodoDTO todoDTO, String email) {
    Todo todo = new Todo(todoDTO.getTitle(), todoDTO.getDescription());
    todo.setUser(findByEmail(email));
    return todoRepository.save(todo);
  }

  public Todo updateTodoForUser(Long todoId, TodoDTO todoDTO, String email) throws AccessDeniedException {
    User user = findByEmail(email);
    Todo todo = findTodoById(todoId);

    verifyUser(todo.getUser(), user);
    todo.setTitle(todoDTO.getTitle());
    todo.setDescription(todoDTO.getDescription());
    todoRepository.save(todo);
    return todo;
  }

  public void deleteTodoForUser(Long todoId, String email) throws AccessDeniedException {
    User user = findByEmail(email);
    Todo todo = findTodoById(todoId);

    verifyUser(todo.getUser(), user);
    todoRepository.deleteById(todoId);
  }
}
