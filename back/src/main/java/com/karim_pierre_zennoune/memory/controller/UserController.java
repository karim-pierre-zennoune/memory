package com.karim_pierre_zennoune.memory.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.service.UserService;

@RestController
public class UserController {

  public UserController(UserService userServ) {
    userService = userServ;

  }

  private UserService userService;

  @GetMapping("/userscores")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ArrayList<ScoreDtoForUserJoin>> getUserScoresById(@RequestParam long id) {
    return new ResponseEntity<>(userService.getUserScoresById(id), HttpStatus.OK);
  }
}
