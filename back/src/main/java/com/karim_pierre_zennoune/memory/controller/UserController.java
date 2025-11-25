package com.karim_pierre_zennoune.memory.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
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

  @GetMapping("/userscores")
  public ResponseEntity<ArrayList<ScoreDtoForUserJoin>> getUserScoresById(@RequestParam long id) {
    return new ResponseEntity<>(userService.getUserScoresById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<UserSessionDto> registerNewUser(@RequestBody UserAuthDto user) {
    User newUser = userService.registerUser(user);
    UserSessionDto ret = new UserSessionDto(newUser.getId(), newUser.getLogin());
    return new ResponseEntity<>(ret, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserSessionDto> loginUser(@RequestBody UserAuthDto user) {
    UserSessionDto ret = userService.loginUser(user);
    if (ret == null) {
      return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    } else {
      return new ResponseEntity<>(ret, HttpStatus.OK);
    }
  }
}
