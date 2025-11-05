package com.karim_pierre_zennoune.memory.controller;


import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

   // insert a user into database
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
      User savedUser = userService.saveUser(user);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //get all the users in the table in our database
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }


}
