package com.hazem.todo_app.controllers;

import com.hazem.todo_app.dto.LoginDTO;
import com.hazem.todo_app.dto.RegisterDTO;
import com.hazem.todo_app.dto.TokenDTO;
import com.hazem.todo_app.services.JWTService;
import com.hazem.todo_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {
  UserService userService;
  JWTService jwtService;

  @Autowired
  public AccessController(UserService userService, JWTService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  ResponseEntity<TokenDTO> registerUser(@RequestBody RegisterDTO registerDTO) {
    return new ResponseEntity<TokenDTO>(
        new TokenDTO(userService.registerUser(registerDTO)),
        HttpStatus.CREATED
    );
  }

  @PostMapping("/login")
  ResponseEntity<TokenDTO> loginUser(@RequestBody LoginDTO loginDTO) {

    return new ResponseEntity<TokenDTO>(
        new TokenDTO(userService.loginUser(loginDTO)),
        HttpStatus.OK
    );
  }
}
