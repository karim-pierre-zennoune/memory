package com.karim_pierre_zennoune.memory.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
// import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
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

    // @GetMapping("/adddata")
    // public ResponseEntity<Score> addData() {
    // for (int i = 0; i < 20; i++) {
    // User user = new User();
    // user.setLogin("ko" + i);
    // user.setPassword("okokok");
    // user = userService.saveUser(user);
    // for (int j = 0; j < 15; j++) {
    // ScoreDtoForInsert scoreDto = new ScoreDtoForInsert((1000 + j + i),
    // user.getId(),
    // new Date(System.currentTimeMillis()));

    // scoreService.saveScore(scoreDto);

    // }
    // System.out.println("added: " + user.getLogin());
    // }
    // return new ResponseEntity<>(null, HttpStatus.CREATED);
    // }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ArrayList<ScoreDto> test() {
        ArrayList<ScoreDto> scoresAsDto = new ArrayList<ScoreDto>();
        return scoresAsDto;
    }

}
