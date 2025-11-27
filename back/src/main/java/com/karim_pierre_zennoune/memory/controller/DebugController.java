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
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.AuthenticationService;
import com.karim_pierre_zennoune.memory.service.ScoreService;
import com.karim_pierre_zennoune.memory.service.UserService;

@RestController
public class DebugController {

    public DebugController(UserService userServ, ScoreService scoreServ, AuthenticationService authenticationService) {
        userService = userServ;
        scoreService = scoreServ;
        this.authenticationService = authenticationService;
    }

    private UserService userService;
    private ScoreService scoreService;
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/adddata")
    public ResponseEntity<Score> addData(@RequestParam String param) {

        for (int i = 0; i < 20; i++) {
            UserAuthDto user = new UserAuthDto(param + i, "qwe");
            User createdUser = authenticationService.signup(user);

            for (int j = 0; j < 15; j++) {
                ScoreDtoForInsert scoreDto = new ScoreDtoForInsert((long) (Math.random() * 10000),
                        createdUser.getId(),
                        new Date(System.currentTimeMillis()));

                scoreService.saveScore(scoreDto);

            }
            System.out.println("added: " + createdUser.getLogin());
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // @GetMapping("/test")
    // @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    // public ArrayList<ScoreDto> test() {
    // ArrayList<ScoreDto> scoresAsDto = new ArrayList<ScoreDto>();
    // return scoresAsDto;
    // }

}
