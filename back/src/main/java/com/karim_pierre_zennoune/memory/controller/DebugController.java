package com.karim_pierre_zennoune.memory.controller;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.service.AuthenticationService;
import com.karim_pierre_zennoune.memory.service.ScoreService;

@RestController
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class DebugController {

    public DebugController(ScoreService scoreServ, AuthenticationService authenticationService) {
        scoreService = scoreServ;
        this.authenticationService = authenticationService;
    }

    private ScoreService scoreService;
    private final AuthenticationService authenticationService;

    @GetMapping("/adddata")
    public ResponseEntity<Score> addData(@RequestParam String param) {

        for (int i = 0; i < 20; i++) {
            UserAuthDto user = new UserAuthDto(param + i, "qwe");
            UserSessionDto createdUser = authenticationService.signup(user);

            for (int j = 0; j < 15; j++) {
                ScoreDtoForInsert scoreDto = new ScoreDtoForInsert((long) (Math.random() * 10000),
                        createdUser.id(),
                        new Date(System.currentTimeMillis()));

                scoreService.saveScore(scoreDto);

            }
            System.out.println("added: " + createdUser.login());
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
