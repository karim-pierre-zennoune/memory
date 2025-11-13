package com.karim_pierre_zennoune.memory.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.ScoreRepository;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import com.karim_pierre_zennoune.memory.service.ScoreService;
import com.karim_pierre_zennoune.memory.service.UserService;

@RestController
public class DebugController {

    public DebugController(UserService userServ, ScoreService scoreServ) {
        userService = userServ;
        scoreService = scoreServ;
    }

    private UserService userService;
    private ScoreService scoreService;

    @GetMapping("/adddata")
    public ResponseEntity<Score> addData() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setLogin("ka" + i);
            user.setPassword("okokok");
            user = userService.saveUser(user);
            for (int j = 0; j < 3; j++) {
                ScoreDto scoreDto = new ScoreDto((1000 + j), user.getId(), new Date(System.currentTimeMillis()));

                scoreService.saveScore(scoreDto);

            }
            System.out.println("added: " + user.getLogin());
        }

        // Score savedScore = scoreService.saveScore(score);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
