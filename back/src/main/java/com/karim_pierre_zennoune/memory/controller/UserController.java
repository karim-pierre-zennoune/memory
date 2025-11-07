package com.karim_pierre_zennoune.memory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.UserService;

@RestController
public class UserController {

  // public UserController(UserService userServ) {
  // userService = userServ;

  // }

  @Autowired
  private UserService userService;

  // insert a user into database
  @PostMapping("/user")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user);
    // return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // get all the users in the table in our database
  @GetMapping("/users")
  public List<User> getUsers() {
    return userService.getUsers();
  }
}
