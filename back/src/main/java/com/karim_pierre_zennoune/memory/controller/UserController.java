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
    
    @Autowired
    private UserService userService;

   // insert a user into database
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
      System.out.println("coucou 1");
      User savedUser = userService.saveUser(user);
       System.out.println("coucou 2");
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //get all the users in the table in our database
    @GetMapping("/users")
    public List<User> getUsers() {
       System.out.println("coucou dans getUsers controller");
        return userService.getUsers();
    }




    //     @PostMapping("/pwet")
    // public User createrUser() {
    //     User user = new User();
    //     user.setLogin("johan");
    //     user.setPassword("blabla");
    //     user.setScore(8000);
    //     // user = userRepo.save(user);
    //     user =userService.saveUser(user);
    //     System.out.println(user);
    //     return user;
    // }


}
