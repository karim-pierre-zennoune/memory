package com.karim_pierre_zennoune.memory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.ScoreService;


@RestController
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/addscore")
    public ResponseEntity<Score> saveScore(@RequestBody Score score){
      try{

        Score savedScore = scoreService.saveScore(score);
        return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
      }
      catch (Exception e) {
        System.out.println(e);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }


    @GetMapping("/scores")
    public List<Score> getScores(){
      return scoreService.getScores();
    }
}
