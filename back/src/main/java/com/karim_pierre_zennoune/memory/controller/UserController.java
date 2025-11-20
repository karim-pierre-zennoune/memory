package com.karim_pierre_zennoune.memory.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.dto.UserLoginDto;
import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

  @GetMapping("/userscores")
  public ArrayList<ScoreDtoForUserJoin> getUserScoresById(@RequestParam long id) {
    System.out.println("in /userscores controller");
    return userService.getUserScoresById(id);

    // return new String();
  }

  @PostMapping("/register")
  public ResponseEntity<UserSessionDto> registerNewUser(@RequestBody UserLoginDto user) {
    User newUser = userService.registerUser(user);
    // do not return user (contains password)
    UserSessionDto ret = new UserSessionDto(newUser.getId(), newUser.getLogin());
    return new ResponseEntity<>(ret, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserSessionDto> loginUser(@RequestBody UserLoginDto user) {
    UserSessionDto ret = userService.loginUser(user);
    if (ret == null) {
      return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    } else {
      return new ResponseEntity<>(ret, HttpStatus.OK);
    }
  }

}
