package com.karim_pierre_zennoune.memory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.UserService;

@RestController
public class UserController {

  public UserController(UserService userServ) {
    userService = userServ;

  }

  private UserService userService;

  @PostMapping("/user")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @GetMapping("/users")
  public ArrayList<UserDto> getUsers() {
    System.out.println("in /users controller");
    return userService.getUsers();
  }
}
